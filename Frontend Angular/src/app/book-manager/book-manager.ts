import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, ValidatorFn, AbstractControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { Book } from '../models/book';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-manager',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './book-manager.html',
  styleUrls: ['./book-manager.css'],
})
export class BookManager {
  form!: FormGroup;
  books!: Observable<Book[]>;
  selectedId: number | null = null;
  editMode: boolean = false;

  constructor(
    private fb: FormBuilder,
    private bookService: BookService
  ) { }

  ngOnInit() {
    this.bookService.getAll();

    const nonEmptyTrim: ValidatorFn = (c: AbstractControl) => {
      const v = c.value;
      return v && v.toString().trim().length > 0 ? null : { required: true };
    };

    this.form = this.fb.group({
      bookName: ['', nonEmptyTrim],
      description: [''],
      bookType: [''],
      authors: [''],
    });

    this.books = this.bookService.books;
  }

  insert() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (this.editMode) {
      this.update();
      this.editMode = false;
      return;
    }

    const raw = this.form.value;
    const book: Book = {
      bookName: (raw.bookName || '').toString().trim(),
      description: raw.description || '',
      bookType: raw.bookType || '',
      authors: raw.authors ? raw.authors.split(',').map((a: string) => a.trim()).filter(Boolean) : [],
    };
    this.bookService.insert(book);
    this.form.reset();
  }

  update() {
    if (!this.selectedId) return;
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const raw = this.form.value;
    const changes: Partial<Book> = {
      bookName: (raw.bookName ?? '').toString().trim(),
      description: raw.description ?? '',
      bookType: raw.bookType ?? '',
      authors: raw.authors ? raw.authors.split(',').map((a: string) => a.trim()).filter(Boolean) : [],
    };
    this.bookService.update(this.selectedId, changes);
    this.form.reset();
  }

  delete() {
    if (!this.selectedId) return;
    if (confirm('Are you sure?')) {
      this.bookService.delete(this.selectedId);
      this.reset();
    }
  }

  edit(b: Book) {

    this.editMode = true;
    this.selectedId = b.id ?? null;

    console.log('Selected ID set to:', this.selectedId);

    this.form.setValue({
      bookName: b.bookName || '',
      description: b.description || '',
      bookType: b.bookType || '',
      authors: (b.authors || []).join(', '),
    });

    console.log('Editing book with ID:', this.selectedId);
  }

  reset() {
    this.form.reset();
    this.selectedId = null;
    this.editMode = false;
  }
}
