package org.ahk.scoreboard;

final class Key {

    private final String homeTeamName;
    private final String awayTeamName;

    Key(final String homeTeamName, final String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    String getHomeTeamName() {
        return homeTeamName;
    }

    String getAwayTeamName() {
        return awayTeamName;
    }

    /**
     * THIS IS NOT AUTO GENERATED EQUAL METHOD
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if(homeTeamName.equals(key.homeTeamName) && awayTeamName.equals(key.awayTeamName)) {
            return true;
        }
        return awayTeamName.equals(key.homeTeamName) && homeTeamName.equals(key.awayTeamName);
    }

    /**
     * THIS IS NOT AUTO GENERATED HASH CODE METHOD
     */
    @Override
    public int hashCode() {
        return homeTeamName.hashCode() + awayTeamName.hashCode();
    }
}
