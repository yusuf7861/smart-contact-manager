// Log a message to indicate the script has loaded
// console.log("Script loaded");

// Get the current theme from localStorage or default to "light"
let currentTheme = getTheme();
applyTheme(); // Apply the theme based on the currentTheme value

/**
 * Function to apply the current theme to the HTML element and update the button text
 */
function applyTheme() {
    // Add the current theme class to the HTML element
    document.querySelector("html").classList.add(currentTheme);

    // Set up an event listener for the theme change button
    const changeThemeButton = document.querySelector('#theme_change_btn');
    changeThemeButton.textContent = getButtonText(); // Set initial button text

    changeThemeButton.addEventListener("click", () => {
        // console.log("Theme button is clicked");

        // Remove the current theme class from the HTML element
        document.querySelector('html').classList.remove(currentTheme);

        // Toggle between "light" and "dark" themes
        currentTheme = currentTheme === "dark" ? "light" : "dark";

        // Update the theme in localStorage
        setTheme(currentTheme);

        // Apply the new theme to the HTML element
        document.querySelector("html").classList.add(currentTheme);

        // Update the button text to reflect the new theme
        changeThemeButton.textContent = getButtonText();
    });
}

/**
 * Function to store the selected theme in localStorage
 * @param {string} theme - The theme to be stored
 */
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

/**
 * Function to retrieve the theme from localStorage
 * @returns {string} - The theme retrieved from localStorage or "light" if none is set
 */
function getTheme() {
    return localStorage.getItem("theme") || "light";
}

/**
 * Function to get the appropriate button text based on the current theme
 * @returns {string} - The text to be displayed on the theme change button
 */
function getButtonText() {
    return currentTheme === "dark" ? "Light" : "Dark";
}
