/* jshint esversion: 6 */


document.getElementById('searchbox').addEventListener("keyup", function(e) {
    if (e.keyCode === 13) {
        e.preventDefault();
        document.getElementById('searchbutton').click();
    }
})
document.getElementById('searchbutton').onclick = () => {

fetch("/search?q=" + document.getElementById('searchbox').value)
.then((response) => response.json())
.then((data) => {


         
            document.getElementById("responseSize").innerHTML = 
        "<p>" + data.length + " websites retrieved</p>";
        let results = data.map((page) =>
        `<li><a href="${page.url}">${page.title}</a></li>`)
        .join("\n");

        if (results == 0){
            document.getElementById("noMatchingQuery").innerHTML = "No web page contains the query word " ;
        }

        else {
            document.getElementById("urlList").innerHTML = `<ul>${results}</ul>`;
        }

});

};

document.getElementById('mode').onclick = () => {
var element = document.body;
element.classList.toggle("dark-mode");
}