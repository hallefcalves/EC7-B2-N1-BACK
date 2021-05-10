package ec.ftt.model;

import java.util.Objects;

public class Lofi {
	
	private int id;
	private String name,
				   link,
				   mood;
	private boolean dimension;
	
	public Lofi(int id, String name, String link, String mood, boolean dimension )
	{
		super();
		setId(id);
		setName(name);
		setLink(link);
		setMood(mood);
		setDimension(dimension);
	}
	public Lofi() {}
	
	public Lofi(String parameter) {
		super();
		Integer x = Integer.valueOf(parameter);
		setId(x);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the mood
	 */
	public String getMood() {
		return mood;
	}

	/**
	 * @param mood the mood to set
	 */
	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	 * @return the dimension
	 */
	public boolean isDimension() {
		return dimension;
	}

	/**
	 * @param dimension the dimension to set
	 */
	public void setDimension(boolean dimension) {
		this.dimension = dimension;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", link=" + link + ", mood=" + mood 
				+ ", dimension= " + dimension + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, link, mood, dimension);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Lofi)) {
			return false;
		}
		Lofi other = (Lofi) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && link == other.link
				&& Objects.equals(mood, other.mood);
	}

}

