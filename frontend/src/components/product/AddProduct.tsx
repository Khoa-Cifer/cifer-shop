import { useState } from "react"
import { createProduct } from "../utils/WebService"
import { Button, Form } from "react-bootstrap"

const AddProduct = () => {
    const [newProduct, setNewProduct] = useState({
        name: "",
        desc: "",
        price: "",
        stock: "",
        image: null
    })

    const [successMessage, setSuccessMessage] = useState("")
    const [errorMessage, setErrorMessage] = useState("")
    const [imagePreview, setImagePreview] = useState("")

    const handleNumberInputChange = (e) => {
        const name = e.target.name
        let value = e.target.value
        if (name === "price" || name === "stock") {
            if (!isNaN(value)) {
                value = parseInt(value)
                if (value <= 0) {
                    setErrorMessage("Price or stock must be larger than 0")
                    value = ""
                }
            } else {
                value = ""
            }
        }
        setNewProduct({ ...newProduct, [name]: value })
    }

    const handleStringInputChange = (e) => {
        const name = e.target.name
        const value = e.target.value
        if (name === "name" || name === "desc") {
            if (name.length > 0) {
                setNewProduct({ ...newProduct, [name]: value })
            }
        }
    }

    const handleImageChange = (e) => {
        const selectedImage = e.target.files[0]
        if (selectedImage === null) {
            setNewProduct({ ...newProduct, image: selectedImage })
            setImagePreview(URL.createObjectURL(selectedImage))
        } else {
            setNewProduct({ ...newProduct, image: selectedImage })
            setImagePreview(URL.createObjectURL(selectedImage))
        }

    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        try {
            const successCondition = await createProduct(
                newProduct.name,
                newProduct.desc,
                newProduct.price,
                newProduct.stock,
                newProduct.image
            )

            if (successCondition !== undefined) { //If every created successfully, successCondition will equal to the new product
                setSuccessMessage("A new product was added successfully")
                setNewProduct({ //redefine product to reuse
                    name: "",
                    desc: "",
                    price: "",
                    stock: "",
                    image: null
                })
            } else {
                setErrorMessage("Error adding new product")
            }
        } catch (error) {
            setErrorMessage(error.data)
        }
        setTimeout(() => {
            setSuccessMessage("")
            setErrorMessage("")
        }, 3000)
    }

    return (
        <div className="container my-5">
            <div className="form-container">
                <h1>Add a new product</h1>

                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3">
                        <Form.Label>Product name</Form.Label>
                        <Form.Control
                            placeholder="Enter name"
                            required
                            type="name"
                            id="name"
                            name="name"
                            value={newProduct.name}
                            onChange={handleStringInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Product description</Form.Label>
                        <Form.Control
                            placeholder="Enter description"
                            required
                            type="description"
                            id="desc"
                            name="desc"
                            value={newProduct.desc}
                            onChange={handleStringInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Product price</Form.Label>
                        <Form.Control
                            placeholder="Enter price"
                            required
                            type="number"
                            id="price"
                            name="price"
                            value={newProduct.price}
                            onChange={handleNumberInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Product stock</Form.Label>
                        <Form.Control
                            placeholder="Enter stock"
                            required
                            type="number"
                            id="stock"
                            name="stock"
                            value={newProduct.stock}
                            onChange={handleNumberInputChange}
                        />
                    </Form.Group>

                    <div className="mb-3">
                        <label htmlFor="photo" className="form-label">
                            Room Photo
                        </label>
                        <input
                            required
                            name="image"
                            id="image"
                            type="file"
                            className="form-control"
                            onChange={handleImageChange}
                        />
                        {imagePreview && (
                            <img
                                src={imagePreview}
                                alt="Preview  room photo"
                                style={{ maxWidth: "400px", maxHeight: "400px" }}
                                className="mb-3"></img>
                        )}
                    </div>

                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
                {successMessage && (
                    <div className="alert alert-success fade show"> {successMessage}</div>
                )}

                {errorMessage && <div className="alert alert-danger fade show"> {errorMessage}</div>}
            </div>
        </div>

    )
}

export default AddProduct
