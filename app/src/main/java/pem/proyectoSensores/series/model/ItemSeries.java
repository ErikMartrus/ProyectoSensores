package pem.proyectoSensores.series.model;

import java.io.Serializable;

public class ItemSeries implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -344029098860190065L;
	private String id;
	private String name;
	private String description;
	private String trailer;
	private String image;
	
	public ItemSeries(String id, String name, String description, String image, String trailer) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.trailer = trailer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
}
