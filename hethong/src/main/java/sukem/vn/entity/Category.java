package sukem.vn.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import sukem.vn.model.Product;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int categoryid;

	@Column(name = "name", columnDefinition = "NVARCHAR(50) NOT NULL")
	@NotEmpty(message = "Không được phép rỗng")
	private String categoryname;

	@Column(name = "images", columnDefinition = "NVARCHAR(500) NULL")
	private String images;

	@Column(name = "status")
	private int status;

	@OneToMany(mappedBy = "category")
	private List<Video> videos = new ArrayList<>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products = new ArrayList<>();

	public Category() {
		super();
	}

	public Category(int categoryid, @NotEmpty(message = "Không được phép rỗng") String categoryname, String images,
			int status) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.images = images;
		this.status = status;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Video addVideo(Video video) {
		if (this.videos == null) {
			this.videos = new ArrayList<>();
		}
		this.videos.add(video);
		video.setCategory(this);
		return video;
	}

	public Video removeVideo(Video video) {
		if (this.videos != null) {
			this.videos.remove(video);
			video.setCategory(null);
		}
		return video;
	}

	public Product addProduct(Product product) {
		if (this.products == null) {
			this.products = new ArrayList<>();
		}
		this.products.add(product);
		product.setCategory(this);
		return product;
	}

	public Product removeProduct(Product product) {
		if (this.products != null) {
			this.products.remove(product);
			product.setCategory(null);
		}
		return product;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + ", images=" + images
				+ ", status=" + status + "]";
	}
}