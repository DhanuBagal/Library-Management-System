import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookManager } from './book-manager';

describe('BookManager', () => {
  let component: BookManager;
  let fixture: ComponentFixture<BookManager>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookManager]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookManager);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
