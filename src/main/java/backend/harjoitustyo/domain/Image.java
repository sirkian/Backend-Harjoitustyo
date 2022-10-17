package backend.harjoitustyo.domain;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "image")
public class Image {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "image_id", nullable = false, updatable = false)
	private Long imageId;
	
	@Column(name = "file_name")
	private String fileName;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Lob
	private byte[] data;
	
	@Size(min = 1, max = 50)
	@Column(name = "image_title")
	private String imageTitle;
	
	@Size(max = 450)
	@Column(name = "image_desc")
	private String imageDesc;
	
	@Column(name = "image_date")
	private Date imageDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id") private AppUser appUser;
	  
	@ManyToOne(fetch = FetchType.EAGER)  
	@JoinColumn(name = "category_id") private Category category;
	 
	
	public Image() {
		
	}
	
	public Image(String fileName, String fileType, byte[] data) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	
	public Image( 
			String fileName, String fileType, byte[] data, String
			imageTitle, String imageDesc, Date imageDate, 
			AppUser appUser, Category category) { 
		super(); 
		this.fileName = fileName; 
		this.fileType = fileType;
		this.data = data; 
		this.imageTitle = imageTitle; 
		this.imageDesc = imageDesc;
		this.imageDate = imageDate; 
		this.appUser = appUser; 
		this.category = category;
	}
	 

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getImageDesc() {
		return imageDesc;
	}

	public void setImageDesc(String imageDesc) {
		this.imageDesc = imageDesc;
	}

	public Date getImageDate() {
		return imageDate;
	}

	public void setImageDate(Date imageDate) {
		this.imageDate = imageDate;
	}

	
	public AppUser getAppUser() { 
		return appUser; 
	}
	  
	public void setAppUser(AppUser appUser) { 
		this.appUser = appUser; 
	}
	  
	public Category getCategory() { 
		return category; 
	}
	  
	public void setCategory(Category category) { 
		this.category = category; 
	}
	 

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", fileName=" + fileName + ", fileType=" + fileType + ", data="
				+ Arrays.toString(data) + ", imageTitle=" + imageTitle + ", imageDesc=" + imageDesc + ", imageDate="
				+ imageDate + "]";
	}	
}
