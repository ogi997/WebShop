export interface Page {
  content: any[]; // Niz elemenata na trenutnoj stranici
  totalPages: number; // Ukupan broj stranica
  totalElements: number; // Ukupan broj elemenata
  size: number; // Broj elemenata po stranici
  number: number; // Trenutni broj stranice (počinje od 0)
}
