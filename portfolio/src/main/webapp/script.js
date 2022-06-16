// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * chose a random element from an array
 */
function randomFromArray(arr){
  randomChoice = Math.floor(Math.random() * arr.length);
  return arr[randomChoice];
}

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!', "I'm related to Paul Revere", "Never tickle a sleeping Dragon"];

  // Pick a random greeting.
  const greeting = randomFromArray(greetings);

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

let pics = ["images/favoritePhotography/beach.jpg", "images/favoritePhotography/Flower.jpg", "images/favoritePhotography/moon.jpg", "images/favoritePhotography/rockRain.jpg", "images/favoritePhotography/sunset.jpg"];

/**
 * Populates div with my best photography
 */
function randomPic() {
  //get img
  picElem = document.getElementById("favorite-pic");
  //chose random img from options
  picElem.src = randomFromArray(pics);
  //make image visible
  picElem.style.display = "block";
}

/**
 * Fetches the response dynamically from the /hello servlet
 */
async function doHello() {
    const response = await fetch("/hello");
    const respJson = await response.json();
    console.log(respJson)

    //gets <p> to insert text
    const respDisplayElem = document.getElementById("dynamicHello");
    respDisplayElem.innerText = randomFromArray(respJson);
}

var currentLangElem;
async function translatePage(langCode){  
    //change disabled button to the selected button
    currentLangElem.disabled = false;
    currentLangElem = document.getElementById(langCode);
    currentLangElem.disabled = true;
    
    document.body.querySelectorAll('p').forEach(translateElem, langCode);
}

async function translateElem(node){
    var text = node.innerText;

    node.innerText = "Loading...";

    //set up POST data
    const params = new URLSearchParams();
    params.append('text', text);
    params.append('langCode', this);

    fetch('/translate', {
      method: 'POST',
      body: params
    }).then(response => response.text())
    .then((translatedMessage) => {
      node.innerHTML = translatedMessage;
    });
}