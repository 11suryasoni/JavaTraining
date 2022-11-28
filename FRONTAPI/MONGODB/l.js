var coinsData = []
// var current_page = 1
// var records_per_page = 10;
var apiUrl = "http://localhost:8080/MONGODB/webapi/myresource/find";

const pageSize = 5;
let curPage = 1;


function handleSearch() {
  var val = document.getElementById("Text").value
  apiUrl = "http://localhost:8080/MONGODB/webapi/myresource/search/" + val;
  renderTable()
}

function handleSort() {
  var val1 = document.getElementById("field").value
  var val2 = document.getElementById("order").value
  apiUrl = "http://localhost:8080/MONGODB/webapi/myresource/sort/" + val1 + "/" + val2;
  renderTable()
}


async function renderTable(page = 1) {
  await getData()

  if (page == 1) {
    prevButton.style.visibility = "hidden";
  } else {
    prevButton.style.visibility = "visible";
  }

  if (page == numPages()) {
    nextButton.style.visibility = "hidden";
  } else {
    nextButton.style.visibility = "visible";
  }

  // create html
  var cryptoCoin = "";
  coinsData.filter((row, index) => {
    let start = (curPage - 1) * pageSize;
    let end = curPage * pageSize;
    if (index >= start && index < end) return true;
  }).forEach(((coin, index) => {
    cryptoCoin += "<tr>";
    cryptoCoin += `<td> ${coin.objectID} </td>`;
    cryptoCoin += `<td> ${coin.isHighlight} </td>`;
    cryptoCoin += `<td> ${coin.accessionNumber} </td>`;
    cryptoCoin += `<td> ${coin.accessionYear} </td>`;
    cryptoCoin += `<td> ${coin.isPublicDomain} </td>`;
    cryptoCoin += `<td> ${coin.primaryImage} </td>`;
    cryptoCoin += `<td> ${coin.primaryImageSmall} </td>`;
    cryptoCoin += `<td> ${coin.additionalImages} </td>`;
    cryptoCoin += `<td> ${coin.constituents} </td>`;
    cryptoCoin += `<td> ${coin.department} </td>`;
    cryptoCoin += `<td> ${coin.objectName} </td>`;
    cryptoCoin += `<td> ${coin.title} </td>`;
    cryptoCoin += `<td> ${coin.culture} </td>`;
    cryptoCoin += `<td> ${coin.period} </td>`;
    cryptoCoin += `<td> ${coin.dynasty} </td>`;
    cryptoCoin += `<td> ${coin.reign} </td>`;
    cryptoCoin += `<td> ${coin.portfolio} </td>`;
    cryptoCoin += `<td> ${coin.artistRole} </td>`;
    cryptoCoin += `<td> ${coin.artistPrefix} </td>`;
    cryptoCoin += `<td> ${coin.artistDisplayName} </td>`;
    cryptoCoin += `<td> ${coin.artistDisplayBio} </td>`;
    cryptoCoin += `<td> ${coin.artistSuffix} </td>`;
    cryptoCoin += `<td> ${coin.artistAlphaSort} </td>`;
    cryptoCoin += `<td> ${coin.dynasty} </td>`;
    cryptoCoin += `<td> ${coin.reign} </td>`;



    "<tr>";
  }));
  document.getElementById("data").innerHTML = cryptoCoin;
  console.log(curPage)
  document.querySelector("#pageNumber").innerText = curPage;
}

function previousPage() {
  if (curPage > 1) {
    curPage--;
    renderTable(curPage);
  }
}

function nextPage() {
  if ((curPage * pageSize) < coinsData.length) {
    curPage++;
    renderTable(curPage);
  }
}
renderTable()

function numPages() {
  return Math.ceil(coinsData.length / pageSize);
}

document.querySelector('#nextButton').addEventListener('click', nextPage, false);
document.querySelector('#prevButton').addEventListener('click', previousPage, false);
//Fetch Data from API
async function getData() {
  const response = await fetch(apiUrl)
  const coins = await response.json()
  //coinsData = coins.data.coins
  coinsData = coins;
  console.log(coinsData)

}