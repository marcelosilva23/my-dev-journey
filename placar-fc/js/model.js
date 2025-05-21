const API_KEY = "8f2339d22dmsh2f4f8825e9d6081p1b7e61jsn75d7afe643d2";
const API_HOST = "api-football-v1.p.rapidapi.com";
const API_URL = "https://api-football-v1.p.rapidapi.com/v3/fixtures";

export async function getTodayMatches() {
    const today = new Date().toISOString().split("T")[0]; //formato YYYY-MM-DD

    const url = `${API_URL}?date=${today}`;

    const options = {
        method: "GET",
        headers: {
           "x-rapidapi-key": API_KEY,
           "x-rapidapi-host": API_HOST,
        },
    };

    try {
        const response = await fetch(url, options);
        const data = await response.json();

        //returns only relevant games
        return data.response.map(match => ({
            id: match.fixture.id,
            home: match.teams.home.name,
            away: match.teams.away.name,
            homeLogo: match.teams.home.logo,
            awayLogo: match.teams.away.logo,
            goals: match.goals,
            status: match.fixture.status.short,
            time: match.fixture.date,
        }));
    } catch (err) {
        console.error("Error, data not found", err);
        throw err;
    }
}