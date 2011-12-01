package org.lecharpentier.api.allocine;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * The Class MovieDetails.
 * 
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
public final class MovieDetails {

	private long filmCode = -1;
	private String movieType = "";
	private String originalTitle = "";
	private String title = "";
	private long productionYear = -1;
	private String nationality = "";
	private Collection<String> genres = new ArrayList<String>();
	private Date releaseDate;
	private long runtime = -1;
	private String synopsis = "";
	private Collection<Person> directors = new ArrayList<Person>();
	private Collection<Person> actors = new ArrayList<Person>();
	private String poster = "";
	private String trailer = "";
	private String link = "";
	private HashMap<String, Long> statistics = new HashMap<String, Long>();

	private MovieDetails() {
	}

	/**
	 * Gets the film code.
	 * 
	 * @return the film code
	 */
	public long getFilmCode() {
		return filmCode;
	}

	/**
	 * Gets the movie type.
	 * 
	 * @return the movie type
	 */
	public String getMovieType() {
		return movieType;
	}

	/**
	 * Gets the original title.
	 * 
	 * @return the original title
	 */
	public String getOriginalTitle() {
		return originalTitle;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the production year.
	 * 
	 * @return the production year
	 */
	public long getProductionYear() {
		return productionYear;
	}

	/**
	 * Gets the nationality.
	 * 
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Gets the genres.
	 * 
	 * @return the genres
	 */
	public Collection<String> getGenres() {
		return Collections.unmodifiableCollection(genres);
	}

	/**
	 * Gets the release date.
	 * 
	 * @return the release date
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Gets the duration of the film in seconds.
	 * 
	 * @return the runtime
	 */
	public long getRuntime() {
		return runtime;
	}

	/**
	 * Gets the synopsis.
	 * 
	 * @return the synopsis
	 */
	public String getSynopsis() {
		return synopsis;
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

	/**
	 * Gets the poster url.
	 * 
	 * @return the poster
	 */
	public String getPoster() {
		return poster;
	}

	/**
	 * Gets the trailer url.
	 * 
	 * @return the trailer
	 */
	public String getTrailer() {
		return trailer;
	}

	/**
	 * Gets the link url.
	 * 
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Gets the statistics.
	 * 
	 * @return the statistics
	 */
	public HashMap<String, Long> getStatistics() {
		return statistics;
	}

	private void setFilmCode(long filmCode) {
		this.filmCode = filmCode;
	}

	private void setMovieType(String movieType) {
		this.movieType = movieType;
	}

	private void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private void setProductionYear(long productionYear) {
		this.productionYear = productionYear;
	}

	private void setNationality(String nationality) {
		this.nationality = nationality;
	}

	private void addGenre(String genre) {
		this.genres.add(genre);
	}

	private void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	private void setRuntime(long runtime) {
		this.runtime = runtime;
	}

	private void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	private void addDirector(Person director) {
		this.directors.add(director);
	}

	private void addActor(Person actor) {
		this.actors.add(actor);
	}

	private void setPoster(String poster) {
		this.poster = poster;
	}

	private void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	private void setLink(String link) {
		this.link = link;
	}

	private void putStatistic(String name, Long statistic) {
		this.statistics.put(name, statistic);
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		return "MovieDetails [filmCode=" + filmCode + ", movieType="
				+ movieType + ", originalTitle=" + originalTitle + ", title="
				+ title + ", productionYear=" + productionYear
				+ ", nationality=" + nationality + ", genres=" + genres
				+ ", releaseDate=" + releaseDate + ", runtime=" + runtime
				+ ", synopsis=" + synopsis + ", directors=" + directors
				+ ", actors=" + actors + ", poster=" + poster + ", trailer="
				+ trailer + ", link=" + link + ", statistics=" + statistics
				+ "]";
	}

	/**
	 * A factory for creating MovieDetails objects.
	 */
	public static class MovieDetailsFactory {
		private final Logger logger = Logger.getLogger(MovieDetailsFactory.class.getName());
		private MovieDetails md;

		/**
		 * Creates an empty MovieDetails instance.
		 */
		public void create() {
			md = new MovieDetails();
		}

		/**
		 * Enable to get the created and filled MovieDetails instance. If this
		 * method is called twice before calling the create method, the second
		 * call will return a null reference.
		 * 
		 * @return the movie details
		 */
		public MovieDetails validate() {
			MovieDetails t = md;
			md = null;
			return t;
		}

		/**
		 * Sets the film code.
		 * 
		 * @param filmCode
		 *            the new film code
		 */
		public void setFilmCode(long filmCode) {
			md.setFilmCode(filmCode);
		}

		/**
		 * Sets the movie type.
		 * 
		 * @param movieType
		 *            the new movie type
		 */
		public void setMovieType(String movieType) {
			md.setMovieType(movieType);
		}

		/**
		 * Sets the original title.
		 * 
		 * @param originalTitle
		 *            the new original title
		 */
		public void setOriginalTitle(String originalTitle) {
			md.setOriginalTitle(originalTitle);
		}

		/**
		 * Sets the title.
		 * 
		 * @param title
		 *            the new title
		 */
		public void setTitle(String title) {
			md.setTitle(title);
		}

		/**
		 * Sets the production year.
		 * 
		 * @param productionYear
		 *            the new production year
		 */
		public void setProductionYear(long productionYear) {
			md.setProductionYear(productionYear);
		}

		/**
		 * Sets the nationality.
		 * 
		 * @param nationality
		 *            the new nationality
		 */
		public void setNationality(String nationality) {
			md.setNationality(nationality);
		}

		/**
		 * Adds the genre.
		 * 
		 * @param genre
		 *            the genre
		 */
		public void addGenre(String genre) {
			md.addGenre(genre);
		}

		/**
		 * Sets the release date.
		 * 
		 * @param dateString
		 *            the new release date
		 */
		public void setReleaseDate(String dateString) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d = sdf.parse(dateString);
				md.setReleaseDate(d);
			} catch (ParseException e) {
				logger.warn(e);
			}
		}

		/**
		 * Sets the runtime.
		 * 
		 * @param runtime
		 *            the new runtime
		 */
		public void setRuntime(long runtime) {
			md.setRuntime(runtime);
		}

		/**
		 * Sets the synopsis.
		 * 
		 * @param synopsis
		 *            the new synopsis
		 */
		public void setSynopsis(String synopsis) {
			md.setSynopsis(synopsis);
		}

		/**
		 * Adds the director.
		 * 
		 * @param director
		 *            the director
		 */
		public void addDirector(Person director) {
			md.addDirector(director);
		}

		/**
		 * Adds the actor.
		 * 
		 * @param actor
		 *            the actor
		 */
		public void addActor(Person actor) {
			md.addActor(actor);
		}

		/**
		 * Sets the poster.
		 * 
		 * @param poster
		 *            the new poster
		 */
		public void setPoster(String poster) {
			md.setPoster(poster);
		}

		/**
		 * Sets the trailer.
		 * 
		 * @param trailer
		 *            the new trailer
		 */
		public void setTrailer(String trailer) {
			md.setTrailer(trailer);
		}

		/**
		 * Sets the link.
		 * 
		 * @param link
		 *            the new link
		 */
		public void setLink(String link) {
			md.setLink(link);
		}

		/**
		 * Put statistic.
		 * 
		 * @param name
		 *            the name
		 * @param statistic
		 *            the statistic
		 */
		public void putStatistic(String name, Long statistic) {
			md.putStatistic(name, statistic);
		}
	}
}
