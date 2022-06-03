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
  randomChoice = Math.floor(Math.random() * arr.length)
  return randomChoice
}

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!', "I'm related to Paul Revere", "Never tickle a sleeping Dragon"];

  // Pick a random greeting.
  const greeting = greetings[randomFromArray(greetings)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

let picShowing = false

let pics = ["images/favoritePhotography/beach.jpg", "images/favoritePhotography/Flower.jpg", "images/favoritePhotography/moon.jpg", "images/favoritePhotography/rockRain.jpg", "images/favoritePhotography/sunset.jpg"]

/**
 * Populates div with my best photography
 */
function randomPic() {
  //get img
  picElem = document.getElementById("favorite-pic")
  //chose random img from options
  picElem.src = pics[randomFromArray(pics)]
  //make image visible
  picElem.style.display = "block"
}