const url = "http://localhost:8080/task/user/2";

function hideLoader(){
    document.getElementById("loading").style.display = "none";
}

function show(tasks){

    let tabela = `
        <thead>
            <th scope"col">#</th>
            <th scope"col">Descrição</th>
            <th scope"col">Username</th>
            <th scope"col">User Id</th>
        </thead>`;

        for(let task of tasks) {
            tabela += `
                <tr>
                    <td scope="row">${task.id}</td>
                    <td> ${task.description}</td>
                    <td> ${task.username}</td>
                    <td> ${task.user.id}</td>
                </tr>
            `
        }

        document.getElementById("tasks").innerHTML = tabela;
}

async function getAPI(url){
    const response = await fetch(url, {method: "GET"});

    var data= await response.json();
    if(response){
        hideLoader();
    }
    show(data);

}

getAPI(url);