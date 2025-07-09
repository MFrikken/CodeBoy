function handleFileProcessing() {
    if (!window.fileController || !window.fileReader) {
        console.error("Java Bridge not ready yet.");
    }
    let filePath = window.fileReader.getFilePath();
    if (!filePath) return;

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
