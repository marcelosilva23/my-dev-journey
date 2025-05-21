import { initApp } from "./controller.js";

document.addEventListener("DOMContentLoaded", () => {
    initApp();

    //Update every 60sec
    setInterval(() => {
        initApp();
    }, 60000);
});