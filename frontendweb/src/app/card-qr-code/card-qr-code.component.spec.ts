import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardQrCodeComponent } from './card-qr-code.component';

describe('CardQrCodeComponent', () => {
  let component: CardQrCodeComponent;
  let fixture: ComponentFixture<CardQrCodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardQrCodeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardQrCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
