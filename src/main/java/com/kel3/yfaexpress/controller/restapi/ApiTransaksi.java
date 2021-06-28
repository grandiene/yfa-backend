package com.kel3.yfaexpress.controller.restapi;

import com.kel3.yfaexpress.model.dto.TransaksiDto;
import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.model.entity.Penerima;
import com.kel3.yfaexpress.model.entity.Pengirim;
import com.kel3.yfaexpress.repository.KurirRepository;
import com.kel3.yfaexpress.repository.TransaksiRepository;
import com.kel3.yfaexpress.repository.UserRepository;
import com.kel3.yfaexpress.service.TransaksiService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin("http//localhost:3000")
@RequestMapping("/api/transaksi")
public class ApiTransaksi {
    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransaksiService transaksiService;

    @Autowired
    private KurirRepository kurirRepository;

    @GetMapping()
    public List<TransaksiDto> getListTransaksi() {
        List<Transaksi> transaksiList = transaksiRepository.findAll();
        List<TransaksiDto> transaksiDtos =
                transaksiList.stream()
                        .map(transaksi -> mapTransaksiToTransaksiDto(transaksi))
                        .collect(Collectors.toList());
        return transaksiDtos;
    }

    @GetMapping("/history/{email}")
    public List<TransaksiDto> getHistory(@PathVariable String email) {
        List<Transaksi> transaksiList = transaksiRepository.findAllByUseraa_Email(email);
        List<TransaksiDto> transaksiDtos =
                transaksiList.stream()
                        .map(transaksi -> mapTransaksiToTransaksiDto(transaksi))
                        .collect(Collectors.toList());
//        System.out.println(authentication.getName());
        return transaksiDtos;
    }


    @GetMapping("/{id}")
    public TransaksiDto getTransaksi(@PathVariable Integer id) {
        Transaksi transaksi = transaksiRepository.findById(id).get();
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        transaksiDto.setIdTransaksi(transaksi.getIdTransaksi());
        return transaksiDto;
    }

    @GetMapping("/admin")
    public List<TransaksiDto> getListTransaksiAdmin() {
        List<Transaksi> transaksiList = transaksiRepository.findAllByStatusDeliveryIsNotInAndIsDeleteEquals(Collections.singletonList("Terkirim"), 0);
        List<TransaksiDto> transaksiDtos =
                transaksiList.stream()
                        .map(transaksi -> mapTransaksiToTransaksiDto(transaksi))
                        .collect(Collectors.toList());
        return transaksiDtos;
    }

    @GetMapping("/resi/{noResi}")
    public TransaksiDto getResi(@PathVariable String noResi) {
        Transaksi transaksi = transaksiRepository.findByResiEquals(noResi);
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        return transaksiDto;
    }

    @PostMapping
    public TransaksiDto save(@RequestBody TransaksiDto transaksiDto) {
        Pengirim pengirim = modelMapper.map(transaksiDto, Pengirim.class);
        Penerima penerima = modelMapper.map(transaksiDto, Penerima.class);
        Transaksi transaksi = modelMapper.map(transaksiDto, Transaksi.class);
        transaksi.setPenerima(penerima);
        transaksi.setPengirim(pengirim);
        transaksi.setIdUser(userRepository.findByEmail(transaksiDto.getEmail()).getIdUser());
        transaksi.setStatusDelivery("Menunggu Pembayaran");
        transaksi.setFotoPenerima("penerima.jpg");
        transaksi.setPenerimaPaket("penerima");
        transaksi.setIsDelete(0);
        transaksi.setIdKurir(1);
        transaksiService.saveTransaksiMaterDetail(transaksi);
        TransaksiDto transaksiDtoDB = mapTransaksiToTransaksiDto(transaksi);
        return transaksiDtoDB;
    }

    private TransaksiDto mapTransaksiToTransaksiDto(Transaksi transaksi) {
        TransaksiDto transaksiDto = modelMapper.map(transaksi, TransaksiDto.class);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        return transaksiDto;
    }

    @GetMapping("/getFoto/{idTransaksi}")
    public byte[] getFoto(@PathVariable Integer idTransaksi) throws IOException {
        Transaksi transaksi = transaksiRepository.findById(idTransaksi).get();
        String userFolderPath = "D:/img/";
        String pathFile = userFolderPath + transaksi.getFotoPenerima();
        Path paths = Paths.get(pathFile);
        byte[] foto = Files.readAllBytes(paths);
        return foto;
    }

    @GetMapping("/getImage/{idTransaksi}")
    public String getFotoBase64(@PathVariable Integer idTransaksi) throws IOException {
        Transaksi transaksi = transaksiRepository.findById(idTransaksi).get();
        String userFolderPath = "D:/img/";
        String pathFile = userFolderPath + transaksi.getFotoPenerima();
        Path paths = Paths.get(pathFile);
        byte[] foto = Files.readAllBytes(paths);
        String img = Base64.getEncoder().encodeToString(foto);
        return img;
    }

    @PostMapping(value="/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Transaksi editSave(@RequestPart(value = "transaksi", required = true) TransaksiDto transaksiDto,
                                 @RequestPart(value = "foto", required = false) MultipartFile file) throws Exception {

        Pengirim pengirim = modelMapper.map(transaksiDto, Pengirim.class);
        Penerima penerima = modelMapper.map(transaksiDto, Penerima.class);
        Transaksi transaksi = modelMapper.map(transaksiDto, Transaksi.class);
        transaksi.setPenerima(penerima);
        transaksi.setPengirim(pengirim);

        if (file == null) {
            System.out.println("halloo");
            transaksi.setFotoPenerima(transaksiRepository.findByIdTransaksi(transaksiDto.getIdTransaksi()).getFotoPenerima());
        } else {
            String userFolderPath = "D:/img";
//                System.getProperty("user.dir").replace('\\', '/') +"/demo/src/main/resources/static/img";
            Path path = Paths.get(userFolderPath);
            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Upload file with size" + file.getSize() + " with name :  " + file.getOriginalFilename());
            transaksi.setFotoPenerima(file.getOriginalFilename());
        }


//        transaksi.setIdUser(userRepository.findByEmail(transaksiDto.getEmail()).getId());
        transaksi.setIdUser(transaksiRepository.findById(transaksiDto.getIdTransaksi()).get().getIdUser());
        transaksi.setUseraa(userRepository.findById(transaksi.getIdUser()).get());
        transaksi.setTanggalTransaksi(transaksiRepository.findById(transaksiDto.getIdTransaksi()).get().getTanggalTransaksi());
        transaksi.setResi(transaksiRepository.findById(transaksiDto.getIdTransaksi()).get().getResi());
        transaksi.setKurir(kurirRepository.findById(transaksiDto.getIdKurir()).get());
        String status = transaksiDto.getStatusDelivery();

        if (status.equalsIgnoreCase("Terkirim")) {
            LocalDateTime objDate = LocalDateTime.now();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm:ss");
            String date = objDate.format(dateFormat);
            transaksi.setTanggalSampai(date);
            transaksi = transaksiRepository.save(transaksi);
        } else if (status.equalsIgnoreCase("Sedang diproses")) {
            transaksi.setTanggalSampai(null);
            transaksiService.getResi(transaksi);
        } else {
            transaksi.setTanggalSampai(null);
            transaksi = transaksiRepository.save(transaksi);
        }
        return transaksi;
    }

    @PostMapping("/delete")
    public void delete(@RequestBody TransaksiDto transaksiDto) {
        Pengirim pengirim = modelMapper.map(transaksiDto, Pengirim.class);
        Penerima penerima = modelMapper.map(transaksiDto, Penerima.class);
        Transaksi transaksi = modelMapper.map(transaksiDto, Transaksi.class);
        transaksi.setPenerima(penerima);
        transaksi.setPengirim(pengirim);
        transaksi.setIdUser(userRepository.findByEmail(transaksiDto.getEmail()).getIdUser());
        transaksi.setIsDelete(1);
        transaksiRepository.save(transaksi);
    }

    @DeleteMapping
    @ResponseBody
    public void deleteTableTransaksi() {
        transaksiRepository.deleteAll();
    }

}
