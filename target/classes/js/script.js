function handleFileProcessing() {
    if (!window.fileController || !window.fileReader) {
        console.error("Java Bridge not ready yet.");
    }
    let filePath = window.fileReader.getFilePath();
    if (!filePath) return;

    handleFileSelection(filePath);
    addToRecentFiles(filePath);
    loadRecentFiles();
}

function handleFileSelection(filePath) {
    try {
        const result = processFile(filePath);
        displayStatistics(result);
    } catch (error) {
        console.error(error);
    }
}

function processFile(filePath) {
    const result = window.fileController.process(filePath);
    return JSON.parse(result);
}

function addToRecentFiles(filePath) {
    if (typeof filePath !== "string" || !filePath.trim()) {
        console.warn("Invalid file path provided:", filePath);
        return;
    }

    let recentFiles = [];
    try {
        recentFiles = JSON.parse(localStorage.getItem("recentFiles")) || [];
        if (!Array.isArray(recentFiles)) throw new Error();
    } catch {
        recentFiles = [];
    }

    recentFiles = recentFiles.filter(f => f !== filePath);
    recentFiles.unshift(filePath);
    recentFiles = recentFiles.slice(0, 5);

    localStorage.setItem("recentFiles", JSON.stringify(recentFiles));
}


function displayStatistics(statistics) {
    let statisticsList = document.getElementById("statistics");
    statisticsList.innerHTML = "";
    Object.entries(statistics).forEach(([key, value]) => {
        let li = document.createElement("li");
        li.textContent = `${key}: ${value}`
        statisticsList.appendChild(li);
    });
}

function fetchAllVulnerabilities() {
    const vulnerabilities = JSON.parse(window.fileController.fetchAllVulnerabilities());
    updateList(vulnerabilities);
}

function updateList(entities) {
    console.log(entities);
    let entityList = document.getElementById("list");
    entityList.innerHTML = "";
    entities.forEach(entity => {
        let li = document.createElement("li");
        li.textContent = `${entity.name}`;
        entityList.appendChild(li);
    });
}

function fetchWeakness(id) {
    let list = document.getElementById("weaknessList");
    const weakness = JSON.parse(window.weaknessController.fetchById(id));
    // TODO: implement
}

function toggleSidebar() {
    const sidebar = document.getElementById("sidebar");
    const showButton = document.getElementById("sidebar-show-button");

    const isHidden = sidebar.classList.toggle("collapsed");

    if (isHidden) {
        showButton.hidden = false;
    } else {
        showButton.hidden = true;
    }
}

function toggleTheme() {
    document.body.classList.toggle("dark-mode");

    // Persist theme choice
    const isDark = document.body.classList.contains("dark-mode");
    localStorage.setItem("theme", isDark ? "dark" : "light");
}

// On page load, apply saved theme
window.addEventListener("DOMContentLoaded", () => {
    const savedTheme = localStorage.getItem("theme");
    if (savedTheme === "dark") {
        document.body.classList.add("dark-mode");
        document.getElementById("theme-switch").checked = true;
    }

    try {
        loadRecentFiles();
    } catch (error) {
        console.error(error);
    }
});

function loadRecentFiles() {
    const recentFiles = JSON.parse(localStorage.getItem("recentFiles") || []);
    const list = document.getElementById("recent-files");
    list.innerHTML = "";

    if (recentFiles) {
        recentFiles.forEach(file => {
            let li = document.createElement("li");
            li.textContent = file.split(/(\\|\/)/g).pop();
            li.title = file;
            li.classList.add("recent-file-item");

            li.addEventListener("click", () => {
                handleFileSelection(file);
            });

            list.appendChild(li);
        });
    }
}

