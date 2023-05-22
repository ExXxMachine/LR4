import {createHeaders} from "./createHeaders.js"
import {createInputs} from "./createInputs.js"
import {book_list} from "./script.js"

export function createBook(book) {
    let cardBook = document.createElement("div")
    cardBook.classList.add("card")
    let cardBody = document.createElement("div")
    cardBody.classList.add("card-body")
    cardBook.append(cardBody)
    let form = document.createElement("form")
    cardBody.appendChild(form)
    let headers = createHeaders(book)
    let inputs = createInputs(book, cardBook, headers)
    form.append(
        ...[headers.name, inputs.name,
            headers.author, inputs.author,
            headers.pageSize, inputs.pageSize,
            headers.publication, inputs.publication,
            headers.price, inputs.price, inputs.buttonsRow])
    book_list.insertBefore(cardBook, book_list.firstChild)
}