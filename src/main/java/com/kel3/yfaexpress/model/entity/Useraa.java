package com.kel3.yfaexpress.model.entity;

import com.kel3.yfaexpress.model.common.CommonModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name =  "users")
public class Useraa extends CommonModel {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	@Column(name = "phone",  nullable = false, unique = true)
	private String phone;
	@Column(name = "firstname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	@Column(name = "usrktp", unique = true)
	private String ktp;
	@Column(name = "usremail", unique = true, nullable = false)
	private String email;
	@Column(name = "usrname", nullable = false, unique = true)
	private String username;
	@Column(name = "userkeyid", unique = true)
	private String userKeyId;
	@Column(name = "usralamat")
	private String alamat;
}
