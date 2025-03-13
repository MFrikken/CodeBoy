function updateList(entities) {
    let entityList = document.getElementById("list");
    entityList.innerHTML = "";
    entities.forEach(entity => {
        let li = document.createElement("li");
        li.textContent = `${entity.name}`;
        entityList.appendChild(li);
    });
}

function addEntity() {
    window.java.addEntity("Theo");
}