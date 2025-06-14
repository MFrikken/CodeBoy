function updateList(entities) {
    let entityList = document.getElementById("list");
    entityList.innerHTML = "";
    entities.forEach(entity => {
        let li = document.createElement("li");
        li.textContent = `${entity.name}`;
        entityList.appendChild(li);
    });
}

function fetchWeakness() {
    const result = window.mainController.process("C:/Users/lenovo/IdeaProjects/SAGE_Java/gl-sast-report.json");
    let list = document.getElementById("weaknessList");

    let weaknesses = [];
    weaknesses.push(JSON.parse(window.weaknessController.fetchById("0")));

    weaknesses.forEach(weakness => {
        let li = document.createElement("li");
        li.textContent = `Type: ${weakness.type}, Name: ${weakness.name}, Value: ${weakness.value}, URL: ${weakness.url}`;
        list.appendChild(li);
    });
}

function parseFile() {
    let filePath = window.fileReader.getFilePath();

    if (filePath) {
        const result = window.mainController.process(filePath);
    } else {
        // display failed status
    }
}

function fetchAllVulnerabilities() {
    window.mainController.fetchAllVulnerabilities();
}