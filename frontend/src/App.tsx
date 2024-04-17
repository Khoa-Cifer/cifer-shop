import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css'
import Home from './components/home/Home'
import CategoryList from './components/category/CategoryList'
import AddProduct from './components/product/AddProduct'
import ProductList from './components/product/ProductList'
import UserList from './components/user/UserList'
import AddUser from './components/user/AddUser'
import { AuthProvider } from './components/auth/AuthProvider'

export default function App() {
  return (
    <AuthProvider>
      <main>
        <Router>
          <Routes>
            <Route path='/' element={<Home />}> </Route>
            <Route path='/add-product' element={<AddProduct />}> </Route>
            <Route path='/register' element={<AddUser />}> </Route>
            <Route path='/product-list' element={<ProductList />}> </Route>
            <Route path='/category-list' element={<CategoryList />}> </Route>
            <Route path='/user-list' element={<UserList />}> </Route>
          </Routes>
        </Router>
      </main>
    </AuthProvider>

  )
}