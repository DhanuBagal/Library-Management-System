import { Book } from '../models/book';

export class BookHelper {
  static parseAuthors(authorsStr: string | null | undefined): string[] {
    return authorsStr
      ? authorsStr.split(',').map((a: string) => a.trim()).filter(Boolean)
      : [];
  }

  static formatAuthors(authors: string[] | undefined): string {
    return (authors || []).join(', ');
  }

  static createBookFromForm(raw: any): Omit<Book, 'id'> {
    return {
      bookName: (raw.bookName || '').toString().trim(),
      description: raw.description || '',
      bookType: raw.bookType || '',
      authors: BookHelper.parseAuthors(raw.authors),
    };
  }

  static createBookChanges(raw: any): Partial<Book> {
    return {
      bookName: (raw.bookName ?? '').toString().trim(),
      description: raw.description ?? '',
      bookType: raw.bookType ?? '',
      authors: BookHelper.parseAuthors(raw.authors),
    };
  }
}
