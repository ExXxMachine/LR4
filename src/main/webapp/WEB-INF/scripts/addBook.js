import {createBook} from "./createBook.js";
import {brandNameInput, capacityInput, books, maxSpeedInput, modelNameInput, priceInput, yearInput} from "./script.js";

export function addBook(event) {
    event.preventDefault()
    let book = {
        name: name.value,
        author: author.value,
        pageSize: pageSize.value,
        publication: publication.value,
        price: price.value,
    }
    fetch("BooksServlet", {
        method: "POST",
        body: JSON.stringify(book)
    })
    if (!cars.find(stored_car => JSON.stringify(stored_car) === JSON.stringify(book))) {
        cars.push(book)
        createBook(book)
    } else {
        alert("Книга уже сть в списке!")
    }
}