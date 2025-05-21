export function renderMatches(matches) {
  const container = document.getElementById("matches");
  container.innerHTML = ""; // limpa antes de renderizar

  matches.forEach(match => {
    const card = document.createElement("div");
    card.className = "match-card";

    card.innerHTML = `
      <div class="teams">
        <div>
          <img src="${match.homeLogo}" alt="${match.home}" width="20"> 
          ${match.home}
        </div>
        <strong>${formatScore(match)}</strong>
        <div>
          ${match.away}
          <img src="${match.awayLogo}" alt="${match.away}" width="20">
        </div>
      </div>
      <div class="status">${translateStatus(match.status)} - ${formatTime(match.time)}</div>
    `;

    container.appendChild(card);
  });
}

function formatScore(match) {
    const { home, away } = match.goals;
    if (home === null || away === null) return "vs";
    return `${home} - ${away}`;
}

function translateStatus(code) {
    const map = {
        FT: "Finished",
        NS: "Not started",
        LIVE: "live",
        HT: "Half-time",
        P: "Penalties",
        TBD: "To be decided",
    };
    return map[code] || code;
    }

function formatTime(dateStr) {
    const date = new Date (dateStr);
    return date.toLocaleTimeString("en-US", {
        hour: "2-digit",
        minute: "2-digit",
    });
}