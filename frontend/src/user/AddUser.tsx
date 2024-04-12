import { useState } from "react"
import { addUser } from "../utils/WebService"
import { Button, Form } from "react-bootstrap"
import defaultProductImage from "../assets/default-product-img.png";

const AddUser = () => {
    const [newProduct, setNewProduct] = useState({
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        address: "",
        phoneNumber: "",
        image: defaultProductImage
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
            const successCondition = await addUser(
                newProduct.firstName,
                newProduct.lastName,
                newProduct.email,
                newProduct.password,
                newProduct.address,
                newProduct.phoneNumber,
                newProduct.image
            )

            if (successCondition !== undefined) { //If every created successfully, successCondition will equal to the new product
                setSuccessMessage("A new product was added successfully")
                setNewProduct({ //redefine product to reuse
                    firstName: "",
                    lastName: "",
                    email: "",
                    password: "",
                    address: "",
                    phoneNumber: "",
                    image: defaultProductImage
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
                <h1>User registration</h1>

                <Form onSubmit={handleSubmit}>
                    <Form.Group className="mb-3">
                        <Form.Label>First name</Form.Label>
                        <Form.Control
                            placeholder="Enter first name"
                            required
                            type="name"
                            id="firstName"
                            name="firstName"
                            value={newProduct.firstName}
                            onChange={handleStringInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Last name</Form.Label>
                        <Form.Control
                            placeholder="Enter last name"
                            required
                            type="name"
                            id="firstName"
                            name="firstName"
                            value={newProduct.lastName}
                            onChange={handleStringInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            placeholder="Enter price"
                            required
                            type="email"
                            id="email"
                            name="email"
                            value={newProduct.email}
                            onChange={handleNumberInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            placeholder="Enter passowrd"
                            required
                            type="password"
                            id="password"
                            name="password"
                            value={newProduct.password}
                            onChange={handleNumberInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Address</Form.Label>
                        <Form.Control
                            placeholder="Enter address"
                            required
                            type="address"
                            id="address"
                            name="address"
                            value={newProduct.address}
                            onChange={handleNumberInputChange}
                        />
                    </Form.Group>

                    <Form.Group className="mb-3">
                        <Form.Label>Phone Number</Form.Label>
                        <Form.Control
                            placeholder="Enter phone number"
                            required
                            type="phoneNumber"
                            id="phoneNumber"
                            name="phoneNumber"
                            value={newProduct.phoneNumber}
                            onChange={handleNumberInputChange}
                        />
                    </Form.Group>

                    <div className="mb-3">
                        <label htmlFor="photo" className="form-label">
                            User Photo
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

export default AddUser
