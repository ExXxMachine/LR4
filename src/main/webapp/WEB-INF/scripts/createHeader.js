export function createHeaders(book) {
    let cardModel = document.createElement("h3")
    cardModel.classList.add("card_title")
    cardModel.textContent = book.name
    let cardAuthor = document.createElement("h4")
    cardAuthor.textContent = `author ${book.author}`
    let cardPageSize = document.createElement("h4")
    cardPageSize.textContent = `pageSize ${book.pageSize}`
    let cardPublication = document.createElement("h4")
    cardPublication.textContent = `publication ${book.publication}`
    let cardPrice = document.createElement("h4")
    cardPrice.textContent = `price ${book.price}`
    return {
        name: name.value,
        author: author.value,
        pageSize: pageSize.value,
        publication: publication.value,
        price: price.value,
    }
}