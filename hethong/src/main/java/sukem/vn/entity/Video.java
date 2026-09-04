package sukem.vn.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@AllArgsConstructor

@Data

@Entity

@Table(name = "Videos")

@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")

public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id

	@Column(name = "videoId")

	private String videoId;

	@Column(name = "active")

	private boolean active;

	@Column(name = "description", columnDefinition = "Nvarchar(500) null")

	private String description;

	@Column(name = "poster", columnDefinition = "Nvarchar(500) null")

	private String poster;

	@Column(name = "title", columnDefinition = "Nvarchar(500) null")

	private String title;

	@Column(name = "views")

	private int views;

	// bi-directional many-to-one association to Category

	@ManyToOne

	@JoinColumn(name = "categoryId")

	private Category categories;

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Category getCategories() {
		return categories;
	}
	
	public void setCategory(Category category) {
	    this.categories = category;
	}
	public void setCategories(Category categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", active=" + active + ", description=" + description + ", poster="
				+ poster + ", title=" + title + ", views=" + views + ", categories=" + categories + "]";
	}

}