import { Component, signal } from '@angular/core';
import { BookManager } from './book-manager/book-manager';

@Component({
  selector: 'app-root',
  imports: [BookManager],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('LibraryManagementApplication');
}
