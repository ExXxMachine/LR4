import {book_list, books} from "./script.js"
import {createHeaders} from "./createHeaders.js";

function createDeleteButton(book, cardBook) {
    let deleteButton = document.createElement("button")
    deleteButton.type = "submit"
    deleteButton.textContent = "Удалить"
    deleteButton.addEventListener("click", clickEvent => {
        clickEvent.preventDefault()
        if (confirm(`Вы уверены что хотите удалить ${book.name} ?`)) {
            book_list.removeChild(cardBook)
            books.splice(cars.indexOf(book), 1)
            fetch("BooksServlet", {
                method: "DELETE",
                body: JSON.stringify(book)
            })
        }
    })
    return deleteButton;
}

function createEditButton(nameInput, authorInput, pageSizeInput, publicationInput, priceInput, saveButton) {
    let editButton = document.createElement("button")
    editButton.type = "submit"
    editButton.textContent = "Изменить"
    editButton.addEventListener("click", clickEvent => {
        clickEvent.preventDefault()
        nameInput.style.display = "block"
        authorInput.style.display = "block"
        pageSizeInput.style.display = "block"
        publicationInput.style.display = "block"
        priceInput.style.display = "block"
        saveButton.style.display = "block"
    })
    return editButton;
}

function createSaveButton(cardBook, headers, book, nameInput, authorInput, pageSizeInput, publicationInput, priceInput, buttonsRow) {
    let saveButton = document.createElement("button")
    saveButton.type = "submit"
    saveButton.style.display = "none"
    saveButton.textContent = "Сохранить"
    let form = cardBook.getElementsByTagName("form");
    form = form[0]
    saveButton.addEventListener("click", clickEvent => {
        clickEvent.preventDefault()
        let newBook = {
            name: name.value,
            author: author.value,
            pageSize: pageSize.value,
            publication: publication.value,
            price: price.value,
        }
        fetch("BooksServlet", {
            method: "PUT",
            body: JSON.stringify([book, newBook])
        })
        headers = createHeaders(newBook)
        form.replaceChildren(...[headers.name, nameInput,
            headers.author, authorInput,
            headers.pageSize, pageSizeInput,
            headers.publication, publicationInput,
            headers.price, priceInput,
            buttonsRow])
        nameInput.style.display = "none"
        authorInput.style.display = "none"
        pageSizeInput.style.display = "none"
        publicationInput.style.display = "none"
        priceInput.style.display = "none"
        saveButton.style.display = "none"
        book= newBook
    })
    return saveButton;
}

export function createInputs(car, cardCar, headers) {
    let nameInput = document.createElement("input")
    nameInput.type = "text"
    let authorInput = document.createElement("input")
    let pageSizeInput = document.createElement("input")
    pageSize.type = "number"
    pageSize.min = "1"
    pageSize.max = "700"
    let publicationInput = document.createElement("input")
    publicationInput.type = "number"
    let priceInput = document.createElement("input")
    priceInput.type = "number"
    let buttonsRow = document.createElement("div")
    let deleteButton = createDeleteButton(book, cardBook);
    let saveButton = createSaveButton(cardBook, headers, book, nameInput, authorInput,  pageSizeInput, publicationInput, priceInput, buttonsRow);
    let editButton = createEditButton(nameInput, authorInput,  pageSizeInput, publicationInput, priceInput, saveButton);
    buttonsRow.appendChild(deleteButton)
    buttonsRow.appendChild(saveButton)
    buttonsRow.appendChild(editButton)
    let inputs = {
        name: nameInput,
        author: authorInput,
        pageSize: pageSizeInput,
        publication: publicationInput,
        price: priceInput,
        buttonsRow: buttonsRow
    }
    for (let property in book) {
        inputs[property].value = book[property]
        inputs[property].required
        inputs[property].style.display = "none"
    }
    return inputs
}