import { getTodayMatches } from "./model.js";
import { renderMatches } from "./view.js";

export async function initApp() {
    try {
        const matches = await getTodayMatches();
        renderMatches(matches);
        updateLasteUpdated();
    } catch (err) {
        document.getElementById("matches").innerHTML = "<p>Error loading games.</p>"
    }
}

function updateLasteUpdated() {
    const now = new Date();
    document.getElementById("last-updated").textContent = now.toLocaleTimeString("en-US");
}