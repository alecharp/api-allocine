package org.lecharpentier.api.allocine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class MovieBrief contains the basic information about a film. It
 * represents the first data of the API to be reachable.
 * 
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
public final class MovieBrief implements Comparable<MovieBrief> {

	private long filmCode = -1;
	private String poster = "";
	private String pageUrl = "";
	private long productionYear = -1;
	private Date releaseDate;
	private String originalTitle = "";
	private String title = "";
	private long pressRating = -1;
	private long usersRating = -1;
	private Collection<Person> directors = new ArrayList<Person>();
	private Collection<Person> actors = new ArrayList<Person>();

	private MovieBrief() {
	}

	/**
	 * Gets the film code.
	 * 
	 * @return the film code
	 */
	public long getFilmCode() {
		return filmCode;
	}

	private void setFilmCode(long filmCode) {
		this.filmCode = filmCode;
	}

	/**
	 * Gets the poster url.
	 * 
	 * @return the img url
	 */
	public String getPoster() {
		return poster;
	}

	private void setPoster(String poster) {
		this.poster = poster;
	}

	/**
	 * Gets the page url.
	 * 
	 * @return the page url
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	private void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * Gets the production year.
	 * 
	 * @return the production year
	 */
	public long getProductionYear() {
		return productionYear;
	}

	private void setProductionYear(long productionYear) {
		this.productionYear = productionYear;
	}

	/**
	 * Gets the release date.
	 * 
	 * @return the release date
	 */
	public Date getReleaseDate() {
		Date t = releaseDate;
		return t;
	}

	private void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Gets the original title.
	 * 
	 * @return the original title
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	private void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the press rating.
	 * 
	 * @return the press rating
	 */
	public long getPressRating() {
		return pressRating;
	}

	private void setPressRating(long pressRating) {
		this.pressRating = pressRating;
	}

	/**
	 * Gets the users rating.
	 * 
	 * @return the users rating
	 */
	public long getUsersRating() {
		return usersRating;
	}

	private void setUsersRating(long usersRating) {
		this.usersRating = usersRating;
	}

	/**
	 * Gets the directors.
	 * 
	 * @return the directors
	 */
	public Collection<Person> getDirectors() {
		return Collections.unmodifiableCollection(directors);
	}

	/**
	 * Gets the actors.
	 * 
	 * @return the actors
	 */
	public Collection<Person> getActors() {
		return Collections.unmodifiableCollection(actors);
	}

	private void addDirector(Person director) {
		this.directors.add(director);
	}

	private void addActor(Person actor) {
		this.actors.add(actor);
	}

	@Override
	public String toString() {
		return "MovieBrief [filmCode=" + filmCode + ", poster=" + poster
				+ ", pageUrl=" + pageUrl + ", productionYear=" + productionYear
				+ ", releaseDate=" + releaseDate + ", originalTitle="
				+ originalTitle + ", title=" + title + ", pressRating="
				+ pressRating + ", usersRating=" + usersRating + ", directors="
				+ directors + ", actors=" + actors + "]";
	}

	static class MovieBriefFactory {
		private final Logger logger = Logger.getAnonymousLogger();
		private MovieBrief mb = null;

		/**
		 * Creates the an empty MovieBrief instance.
		 */
		public void create() {
			mb = new MovieBrief();
		}

		/**
		 * Enable to get the created and filled MovieBrief instance. If this
		 * method is called twice before calling the create method, the second
		 * call will return a null reference.
		 * 
		 * @return the movie brief
		 */
		public MovieBrief validate() {
			MovieBrief t = mb;
			mb = null;
			return t;
		}

		/**
		 * Sets the film code.
		 * 
		 * @param code
		 *            the new film code
		 */
		public void setFilmCode(long code) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setFilmCode(code);
		}

		/**
		 * Sets the poster url.
		 * 
		 * @param imgUrl
		 *            the new img url
		 */
		public void setPoster(String poster) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setPoster(poster);
		}

		/**
		 * Sets the page url.
		 * 
		 * @param pageUrl
		 *            the new page url
		 */
		public void setPageUrl(String pageUrl) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setPageUrl(pageUrl);
		}

		/**
		 * Sets the production year.
		 * 
		 * @param productionYear
		 *            the new production year
		 */
		public void setProductionYear(long productionYear) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setProductionYear(productionYear);
		}

		/**
		 * Sets the release date.
		 * 
		 * @param dateString
		 *            the new release date
		 */
		public void setReleaseDate(String dateString) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(dateString);
				mb.setReleaseDate(d);
			} catch (ParseException e) {
				logger.log(Level.FINER, e.getMessage());
			}
		}

		/**
		 * Sets the original title.
		 * 
		 * @param originalTitle
		 *            the new original title
		 */
		public void setOriginalTitle(String originalTitle) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setOriginalTitle(originalTitle);
		}

		/**
		 * Sets the title.
		 * 
		 * @param title
		 *            the new title
		 */
		public void setTitle(String title) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setTitle(title);
		}

		/**
		 * Sets the press rating.
		 * 
		 * @param pressRating
		 *            the new press rating
		 */
		public void setPressRating(long pressRating) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setPressRating(pressRating);
		}

		/**
		 * Sets the users rating.
		 * 
		 * @param usersRating
		 *            the new users rating
		 */
		public void setUsersRating(long usersRating) {
			if (mb == null) {
				throw new RuntimeException("MovieBrief in MovieBriefFactory "
						+ "has not been initialized");
			}
			mb.setUsersRating(usersRating);
		}

		/**
		 * Adds the director.
		 * 
		 * @param director
		 *            the director
		 */
		public void addDirector(Person director) {
			mb.addDirector(director);
		}

		/**
		 * Adds the actor.
		 * 
		 * @param actor
		 *            the actor
		 */
		public void addActor(Person actor) {
			mb.addActor(actor);
		}
	}

	@Override
	public int compareTo(MovieBrief o) {
		if (o.equals(this)) {
			return 0;
		}
		return this.originalTitle.compareTo(o.originalTitle);
	}
}
