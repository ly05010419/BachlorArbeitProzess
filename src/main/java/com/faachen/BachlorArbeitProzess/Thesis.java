package com.faachen.BachlorArbeitProzess;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "thesis")
public class Thesis implements java.io.Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7913403836244033291L;
	
	private Integer id;
	private Integer studentId;
	private String title;
	private String supervisor;
	private Integer approved;
	

	public Thesis(Integer id, Integer studentId, String title, String supervisor, Integer approved) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.title = title;
		this.supervisor = supervisor;
		this.approved = approved;
	}
	
	public Thesis(){
		
		
	}

	
	public Integer getId() {
		return id;
	}
	
	@XmlElement
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getStudentId() {
		return studentId;
	}
	
	@XmlElement
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	
	public String getTitle() {
		return title;
	}
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getSupervisor() {
		return supervisor;
	}
	@XmlElement
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	
	public Integer getApproved() {
		return approved;
	}
	@XmlElement
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	@Override
	public String toString() {
		return "Thesis [id=" + id + ", studentId=" + studentId + ", title=" + title + ", supervisor=" + supervisor
				+ ", approved=" + approved + "]";
	}
	
	
	
}
