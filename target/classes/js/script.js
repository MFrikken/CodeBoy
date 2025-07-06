function processFile() {
    let filePath = window.fileReader.getFilePath();
    console.log("Processing file called");


    if (filePath) {
        const result = JSON.parse(window.fileController.process(filePath));
        console.log(result)
        displayStatistics(result);
    } else {
        console.log(filePath);
    }
}

function displayStatistics(statistics) {
    console.log(statistics);
    let statisticsList = document.getElementById("statistics");
    statisticsList.innerHTML = "";
    statistics.forEach(severity => {
        let li = document.createElement("li");
        li.textContent = `${severity.key} ${severity.value}`
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
}
