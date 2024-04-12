import './App.css'
import AddProduct from './product/AddProduct'
import ProductList from './product/ProductList'
import AddUser from './user/AddUser'

export default function App() {
  return (
    <div>
      <AddProduct />
      <AddUser />
      <ProductList />
    </div>
  )
}