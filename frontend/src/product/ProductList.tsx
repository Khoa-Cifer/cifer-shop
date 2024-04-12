import { useEffect, useState } from "react"
import defaultProductImage from "../assets/default-product-img.png";
import { getAllProducts } from "../utils/WebService";
import { Col } from "react-bootstrap";

const ProductList = () => {
    const [products, setProducts] = useState([{
        id: "",
        name: "",
        price: "",
        stock: "",
    }])

    const [currentPage, setCurrentPage] = useState(1)
    const [productsPerPage] = useState(8)
    const [isLoading, setIsLoading] = useState(false)

    const [filteredProducts, setFilteredProducts] = useState([{
        id: "",
        name: "",
        price: "",
        stock: "",
    }])

    const [errorMessage, setErrorMessage] = useState("")
    const [successMessage, setSuccessMessage] = useState("")

    const fetchAllProducts = async () => {
        setIsLoading(true)
        try {
            const result = await getAllProducts()
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

    const handlePaginationClick = (pageNumber) => {
        setCurrentPage(pageNumber)
    }

    const calculateTotalPages = (filteredRooms, roomsPerPage, rooms) => {
        const totalRooms = filteredRooms.length > 0 ? filteredRooms.length : rooms.length
        return Math.ceil(totalRooms / roomsPerPage)
    }

    const indexOfLastProduct = currentPage * productsPerPage
    const indexOfFirstProduct = indexOfLastProduct - productsPerPage
    const currentProduct = filteredProducts.slice(indexOfFirstProduct, indexOfLastProduct)

    return (
        <>
            <div className="container col-md-8 col-lg-6">
                {successMessage && <p className="alert alert-success mt-5">{successMessage}</p>}

                {errorMessage && <p className="alert alert-danger mt-5">{errorMessage}</p>}
            </div>

            {isLoading ? (
                <p>Loading existing rooms</p>
            ) : (
                <>
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
                </>
            )}
        </>
    )
}

export default ProductList