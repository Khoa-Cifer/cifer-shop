import { useEffect, useState } from "react"
import { getAllProducts } from "../utils/WebService";

const ProductList = () => {
    const [products, setProducts] = useState([{
        id: "",
        name: "",
        price: "",
        stock: "",
    }])

    const [isLoading, setIsLoading] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const [successMessage, setSuccessMessage] = useState("")
    
    const fetchAllProducts = async () => {
        setIsLoading(true)
        try {
            const result = await getAllProducts()
            console.log(result)
            setProducts(result)
            setIsLoading(false)
        } catch (error) {
            setErrorMessage(error.message)
            setIsLoading(false)
        }
    }

    useEffect(() => {
        fetchAllProducts()
    }, [])

    return (
        <section className="mt-5 mb-5 container">
            <div className="d-flex justify-content-between mb-3 mt-5">
                <h2>Existing Rooms</h2>
            </div>

            <table className="table table-bordered table-hover">
                <thead>
                    <tr className="text-center">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Stock</th>
                    </tr>
                </thead>

                <tbody>
                    {products.map((product) => (
                        <tr key={product.id} className="text-center">
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price}</td>
                            <td>{product.stock}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </section>
    )
}

export default ProductList