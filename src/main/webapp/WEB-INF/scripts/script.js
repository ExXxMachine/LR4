import {createBook} from "./createBook.js";
import {addBook} from "./addBook.js";

export let book_list = document.getElementById("books")
export let books = []
export let nameInput = document.getElementById("name")
export let authorInput = document.getElementById("author")
export let pageSizeInput = document.getElementById("pageSize")
export let publicationInput = document.getElementById("publication")
export let priceInput = document.getElementById("price")
document.getElementsByTagName("form")[0].addEventListener("submit", addBook)

async function books_upload() {
    let response = await fetch("BooksServlet")
    let uploadedBook = await response.json()
    for (let book of uploadedBook) {
        books.push(book)
        createBook(book)
    }
}
books_upload()