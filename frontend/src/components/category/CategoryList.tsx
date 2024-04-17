import { useEffect, useState } from "react"
import { getAllCategories } from "../utils/WebService"

const CategoryList = () => {
    const [categories, setCategories] = useState([{
        id: "",
        name: "",
        productSet: "",
    }])

    const [isLoading, setIsLoading] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const [successMessage, setSuccessMessage] = useState("")

    const fetchAllProducts = async () => {
        setIsLoading(true)
        try {
            const result = await getAllCategories();
            setCategories(result)
            setIsLoading(false)
        } catch (error) {
            setErrorMessage(error.message)
            setIsLoading(false)
        }
    }

    useEffect(() => {
        fetchAllProducts();
    }, [])


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
                                </tr>
                            </thead>

                            <tbody>
                                {categories.map((categories) => (
                                    <tr key={categories.id} className="text-center">
                                        <td>{categories.id}</td>
                                        <td>{categories.name}</td>
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

export default CategoryList