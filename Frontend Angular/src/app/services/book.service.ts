import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Book } from '../models/book';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class BookService {
    //   private nextId = 1;
    private booksSub = new BehaviorSubject<Book[]>([]);
    books = this.booksSub.asObservable();

    private baseUrl = 'http://localhost:8080/books';

    get value(): Book[] {
        return this.booksSub.value;
    }

    constructor(private http: HttpClient) { }

    getAll() {
        this.http.get<any[]>(this.baseUrl)
            .subscribe(books => {
                
                const mappedBooks = books.map(b => {
                    return {
                        id: b.bookId !== undefined ? b.bookId : b.id,
                        bookName: b.bookName,
                        description: b.description || '',
                        bookType: b.bookType,
                        authors: b.authors || []
                    };
                });
                this.booksSub.next(mappedBooks);
            }, err => {
                console.error('Error fetching books:', err);
            });
    }

    insert(book: Book) {
        this.http.post(this.baseUrl + '/add', book, { responseType: 'text' })
            .subscribe(responseMessage => {
                console.log( responseMessage);
                this.getAll();
            }, err => {
                console.error('Book creation failed:', err);
            });

        return {} as Book;
    }

    update(id: number, changes: Partial<Book>) {
        this.http.put(this.baseUrl + '/' + id, changes, { responseType: 'text' })
            .subscribe(responseMessage => {
                console.log('Book update success:', responseMessage);
                this.getAll();
            }, err => {
                console.error('Update failed:', err);
            });
    }

    delete(id: number) {
        this.http.delete(this.baseUrl + '/' + id, { responseType: 'text' })
            .subscribe(responseMessage => {
                console.log('Book delete success:', responseMessage);
                const list = this.booksSub.value.filter(b => b.id !== id);
                this.booksSub.next(list);
            }, err => {
                console.error('Delete failed:', err);
            });
    }

    getById(id: number) {
        return this.booksSub.value.find(b => b.id === id) ?? null;
    }
}