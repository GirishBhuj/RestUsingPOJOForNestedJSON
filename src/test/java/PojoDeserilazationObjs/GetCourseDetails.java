package PojoDeserilazationObjs;


public class GetCourseDetails
{
	private String url;
	private String instructor;
	private String services;
	private String expertise;
	private Courses courses;
	private String linkedIn;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public Courses getcourses() {
		return courses;
	}
	public void setmcourses(PojoDeserilazationObjs.Courses courses) {
		this.courses = courses;
	}
}

