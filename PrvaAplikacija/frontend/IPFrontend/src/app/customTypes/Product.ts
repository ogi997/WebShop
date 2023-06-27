export interface Product {
  id: number,
  title: string,
  description: string,
  fkCategory: number,
  price: number,
  conditionProduct: string,
  location: string,
  fkUser: number | undefined,
  contact: string
}
