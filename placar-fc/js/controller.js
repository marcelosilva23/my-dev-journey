import { getLineup, getTodayMatches } from "./model.js";
import { renderMatches, renderLineup } from "./view.js";

export async function initApp() {
  try {
    const matches = await getTodayMatches();

    renderMatches(matches);
    updateLastUpdated();

    // ADICIONAR OS EVENTOS DEPOIS DE RENDERIZAR OS JOGOS
    document.querySelectorAll(".lineup-btn").forEach(button => {
      button.addEventListener("click", async (e) => {
        const fixtureId = e.target.dataset.id;
        const container = document.getElementById(`lineup-${fixtureId}`);

        if (container.style.display === "none") {
          const lineup = await getLineup(fixtureId);
          renderLineup(container, lineup);
          container.style.display = "block";
          e.target.textContent = "Esconder escalação";
        } else {
          container.style.display = "none";
          e.target.textContent = "Ver escalação";
        }
      });
    });

  } catch (err) {
    console.error("Erro ao carregar os jogos:", err);
    document.getElementById("matches").innerHTML = "<p>Erro ao carregar os jogos.</p>";
  }
}

function updateLastUpdated() {
  const now = new Date();
  document.getElementById("last-updated").textContent = now.toLocaleTimeString("pt-BR");
}
