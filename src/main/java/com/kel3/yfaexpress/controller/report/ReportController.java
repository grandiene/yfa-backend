package com.kel3.yfaexpress.controller.report;

import com.kel3.yfaexpress.configuration.Exception.ReportException;
import com.kel3.yfaexpress.model.dto.TransaksiDto;
import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.report.CustomJRDataSource;
import com.kel3.yfaexpress.repository.TransaksiRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Controller
public class ReportController {
    final static Logger logger = Logger.getLogger(ReportController.class);

    final static String pdfSource = "src/main/resources/static/jasper/report.pdf";
    final static String xlsxSource = "src/main/resources/static/jasper/report.xlsx";


    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private ModelMapper modelMapper;

    private void generateReport(List<TransaksiDto> transaksi) throws JRException{
        logger.info("[!] Start generate report");
        // Path to our template goes here
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/jasper/report.jrxml");
        // load data to datasource
        CustomJRDataSource<TransaksiDto> dataSource = new CustomJRDataSource<TransaksiDto>().using(transaksi);
        // Map datasource to template
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);
        // Export to pdf
        JasperExportManager.exportReportToPdfFile(jasperPrint,pdfSource);
    }

    public byte[] getReportXlsx(final JasperPrint jasperPrint) throws ReportException {
        final JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        final byte[] rawBytes;

        try(final ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){
            xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
            xlsxExporter.exportReport();

            rawBytes = xlsReport.toByteArray();
        } catch (JRException | IOException e) {
            throw new ReportException(e);
        }
        return rawBytes;
    }

    private byte[] generateReportExcel(List<TransaksiDto> transaksi) throws JRException, ReportException{
        logger.info("[!] Start generate report");
        // Path to our template goes here
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/static/jasper/reportXlsx.jrxml");
        // load data to datasource
        CustomJRDataSource<TransaksiDto> dataSource = new CustomJRDataSource<TransaksiDto>().using(transaksi);
        // Map datasource to template
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String,Object>(), dataSource);
        // Export to excel
        return getReportXlsx(jasperPrint);
    }

    @GetMapping(value = "/report/{resi}.pdf")
    public HttpEntity<byte[]> getReport(@PathVariable String resi) throws JRException,IOException {
        // Stub data
//        List<Transaksi> transaction = transaksiRepository.findAll();
        Transaksi transaksi = transaksiRepository.findByResiEquals(resi);
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        List<TransaksiDto> tr = Arrays.asList(transaksiDto);
//        List<Transaksi> transaksi = transaksiRepository.findAll();
        generateReport(tr);
        logger.info("[+] Generated report successfully");

        // Force download
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition",
                "attachment; filename=" + resi + ".pdf");
        byte[] documentBody = Files.readAllBytes(new File(pdfSource).toPath());
        header.setContentLength(documentBody.length);
        return new HttpEntity<byte[]>(documentBody, header);
    }

    @GetMapping(value = "/report/{resi}.xlsx")
    public HttpEntity<byte[]> getReportExcel(@PathVariable String resi) throws JRException,IOException, ReportException {
        // Stub data
        List<Transaksi> transaction = transaksiRepository.findAll();
        Transaksi transaksi = transaksiRepository.findByResiEquals(resi);
        TransaksiDto transaksiDto = new TransaksiDto();
        modelMapper.map(transaksi, transaksiDto);
        modelMapper.map(transaksi.getKurir(), transaksiDto);
        modelMapper.map(transaksi.getPengirim(), transaksiDto);
        modelMapper.map(transaksi.getPenerima(), transaksiDto);
        modelMapper.map(transaksi.getUseraa(), transaksiDto);
        List<TransaksiDto> tr = Arrays.asList(transaksiDto);
//        List<Transaksi> transaksi = transaksiRepository.findAll();

        logger.info("[+] Generated report successfully");

        // Force download
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + resi + ".xlsx");
//        byte[] documentBody = Files.readAllBytes(new File(xlsxSource).toPath());
        final byte[] data = generateReportExcel(tr);

        header.setContentLength(data.length);
        return new HttpEntity<byte[]>(data, header);
    }
}

