package sukem.vn.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@AllArgsConstructor

@NoArgsConstructor

@Data

@Table(name = "categories")

@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "categoryId")

	private int categoryid;

	@Column(name = "categoryname", columnDefinition = "NVARCHAR(50) not null")

	@NotEmpty(message = "Không được phép rỗng")

	private String categoryname;

	@Column(name = "images", columnDefinition = "Nvarchar(500) null")

	private String images;

	@Column(name = "status")
	private int status;

	// bi-directional many-to-one association to Video

	@OneToMany(mappedBy = "categories")

	private List<Video> videos;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int categoryid, @NotEmpty(message = "Không được phép rỗng") String categoryname, String images,
			int status, List<Video> videos) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.images = images;
		this.status = status;
		this.videos = videos;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + ", images=" + images
				+ ", status=" + status + ", videos=" + videos + "]";
	}

	public Video addVideo(Video video) {

		getVideos().add(video);

		video.setCategory(this);

		return video;

	}

	public Video removeVideo(Video video) {

		getVideos().remove(video);

		video.setCategory(null);

		return video;

	}

}
