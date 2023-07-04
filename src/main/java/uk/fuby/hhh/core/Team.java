package uk.fuby.hhh.core;


import java.util.List;

public final class Team {
	private final List<HHHPlayer> players;
	private final TeamColor color;
	private final String name;
	private Integer score;

	public Team(
			List<HHHPlayer> players,
			TeamColor color,
			String name,
			Integer score) {
		this.players = players;
		this.color = color;
		this.name = name;
		this.score = score;
	}

	public List<HHHPlayer> getPlayers() {
		return players;
	}

	public TeamColor getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
