
import { useEffect, useState } from "react";
import {
  LineChart,
  Line,
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer
} from "recharts";
import "./App.css";

function App() {

  /* --------------------------------------------------
     DASHBOARD DATA
  -------------------------------------------------- */

  const weatherData = [
    { day: "Mon", temperature: 30, rainfall: 2 },
    { day: "Tue", temperature: 31, rainfall: 0 },
    { day: "Wed", temperature: 33, rainfall: 4 },
    { day: "Thu", temperature: 32, rainfall: 1 },
    { day: "Fri", temperature: 34, rainfall: 6 },
    { day: "Sat", temperature: 32, rainfall: 3 },
    { day: "Sun", temperature: 31, rainfall: 2 }
  ];

  const cropData = [
    { crop: "Rice", fields: 4 },
    { crop: "Cotton", fields: 3 },
    { crop: "Maize", fields: 2 },
    { crop: "Others", fields: 1 }
  ];


  /* --------------------------------------------------
     CROP OPTIONS
  -------------------------------------------------- */

  const cropOptions = [
    {
      name: "Rice",
      icon: "🌾",
      growthDuration: 120
    },
    {
      name: "Cotton",
      icon: "🌿",
      growthDuration: 160
    },
    {
      name: "Maize",
      icon: "🌽",
      growthDuration: 100
    },
    {
      name: "Wheat",
      icon: "🌾",
      growthDuration: 120
    },
    {
      name: "Groundnut",
      icon: "🥜",
      growthDuration: 110
    },
    {
      name: "Tomato",
      icon: "🍅",
      growthDuration: 90
    },
    {
      name: "Chilli",
      icon: "🌶️",
      growthDuration: 150
    }
  ];

  const soilOptions = [
    "Black soil",
    "Clay soil",
    "Red soil",
    "Sandy soil",
    "Loamy soil",
    "Not specified"
  ];

  const seasonOptions = [
    "Kharif",
    "Rabi",
    "Zaid",
    "Year-round"
  ];


  /* --------------------------------------------------
     MAIN STATE
  -------------------------------------------------- */

  const [page, setPage] = useState(
    window.history.state?.page || "login"
  );
  const navigateTo = (nextPage) => {
    window.history.pushState(
      { page: nextPage },
      "",
      window.location.pathname
    );
  
    setPage(nextPage);
  };
  const [showPassword, setShowPassword] =
    useState(false);

  const [farmerName, setFarmerName] =
    useState("");

    const [farmWeather, setFarmWeather] = useState(null);
    const [weatherLoading, setWeatherLoading] = useState(false);
    const [weatherError, setWeatherError] = useState("");
  /* --------------------------------------------------
     LOGIN STATE
  -------------------------------------------------- */

  const [loginPhone, setLoginPhone] =
    useState("");

  const [loginPassword, setLoginPassword] =
    useState("");


  /* --------------------------------------------------
     SIGNUP STATE
  -------------------------------------------------- */

  const [signupName, setSignupName] =
    useState("");

  const [signupPhone, setSignupPhone] =
    useState("");

  const [signupLocation, setSignupLocation] =
    useState("");

  const [signupPassword, setSignupPassword] =
    useState("");


  /* --------------------------------------------------
     ADD FARM STATE
  -------------------------------------------------- */

  const [showAddFarm, setShowAddFarm] =
    useState(false);

  const [farmName, setFarmName] =
    useState("");

  const [farmArea, setFarmArea] =
    useState("");

  const [farmLocation, setFarmLocation] =
    useState("");


  /* --------------------------------------------------
     ADD CROP STATE
  -------------------------------------------------- */

  const [showAddCrop, setShowAddCrop] =
    useState(false);

  const [selectedFarmId, setSelectedFarmId] =
    useState(null);

  const [selectedCrop, setSelectedCrop] =
    useState("");

  const [cropArea, setCropArea] =
    useState("");

  const [cropSoil, setCropSoil] =
    useState("");

  const [cropSeason, setCropSeason] =
    useState("");


  /* --------------------------------------------------
     FARMS
  -------------------------------------------------- */

  const [farms, setFarms] = useState([
    {
      id: 1,
      name: "Green Farm",
      location: "Guntur",
      latitude: 16.3067,
      longitude: 80.4365,
      area: 5,
      icon: "🌾",
      fields: [
        {
          id: 1,
          area: 2,
          soilType: "Clay soil",
          crop: "Rice",
          cropSeason: "Kharif",
          growthDuration: 120
        },
        {
          id: 2,
          area: 3,
          soilType: "Black soil",
          crop: "Cotton",
          cropSeason: "Kharif",
          growthDuration: 160
        }
      ]
    },
    {
      id: 2,
      name: "Mango Farm",
      location: "Guntur",
      latitude: 16.3067,
      longitude: 80.4365,
      area: 8,
      icon: "🌱",
      fields: []
    }
  ]);

  /* --------------------------------------------------
     LOGIN
  -------------------------------------------------- */

  const handleLogin = async (event) => {
    event.preventDefault();
  
    try {
      const response = await fetch(
        "http://localhost:8081/farmers"
      );
  
      if (!response.ok) {
        throw new Error("Unable to fetch farmers");
      }
  
      const farmers = await response.json();
  
      const farmer = farmers.find(
        (item) =>
          String(item.phone).trim() ===
          String(loginPhone).trim()
      );
  
      if (!farmer) {
        alert("No account found with this phone number.");
        return;
      }
  
      setFarmerName(farmer.name);
      navigateTo("dashboard");
  
    } catch (error) {
      console.error("Login error:", error);
      alert("Unable to login. Please try again.");
    }
  };

  /* --------------------------------------------------
     SIGNUP
  -------------------------------------------------- */

  const handleSignup = async (event) => {
    event.preventDefault();
  
    try {
      const response = await fetch("http://localhost:8081/farmers", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name: signupName,
          phone: signupPhone,
          location: signupLocation
        })
      });
  
      if (!response.ok) {
        throw new Error("Signup failed");
      }
  
      const farmer = await response.json();
  
      console.log("Farmer created:", farmer);
  
      setFarmerName(farmer.name);
      navigateTo("dashboard");
  
    } catch (error) {
      console.error("Signup error:", error);
      alert("Unable to create account. Please try again.");
    }
  };


  /* --------------------------------------------------
     LOGOUT
  -------------------------------------------------- */

  const handleLogout = () => {
    navigateTo("logout");
  };
  useEffect(() => {
    if (!page.startsWith("farm-")) {
      return;
    }
  
    const farmId = Number(page.replace("farm-", ""));
  
    const farm = farms.find(
      (item) => item.id === farmId
    );
  
    if (!farm) {
      return;
    }
  
    const fetchFarmWeather = async () => {
      try {
        setWeatherLoading(true);
        setWeatherError("");
        setFarmWeather(null);
  
        const response = await fetch(
          `http://localhost:8081/weather?latitude=${farm.latitude}&longitude=${farm.longitude}`
        );
  
        if (!response.ok) {
          throw new Error("Weather request failed");
        }
  
        const data = await response.json();
  
        setFarmWeather(data);
      } catch (error) {
        console.error("Weather error:", error);
        setWeatherError(
          "Unable to load weather information."
        );
      } finally {
        setWeatherLoading(false);
      }
    };
  
    fetchFarmWeather();
  }, [page, farms]);

  /* --------------------------------------------------
     ADD FARM
  -------------------------------------------------- */

  const handleAddFarm = (event) => {
    event.preventDefault();

    const newFarm = {
      id: Date.now(),

      name: farmName,

      location: farmLocation,

      area: Number(farmArea),

      icon: "🌾",

      fields: []
    };

    setFarms((currentFarms) => [
      ...currentFarms,
      newFarm
    ]);

    setFarmName("");
    setFarmArea("");
    setFarmLocation("");

    setShowAddFarm(false);
  };


  /* --------------------------------------------------
     OPEN ADD CROP
  -------------------------------------------------- */

  const openAddCrop = (farmId) => {

    setSelectedFarmId(farmId);

    setSelectedCrop("");

    setCropArea("");

    setCropSoil("");

    setCropSeason("");

    setShowAddCrop(true);
  };


  /* --------------------------------------------------
     CLOSE ADD CROP
  -------------------------------------------------- */

  const closeAddCrop = () => {

    setShowAddCrop(false);

    setSelectedFarmId(null);

    setSelectedCrop("");

    setCropArea("");

    setCropSoil("");

    setCropSeason("");
  };


  /* --------------------------------------------------
     ADD CROP
  -------------------------------------------------- */

  const handleAddCrop = (event) => {

    event.preventDefault();

    const selectedFarm = farms.find(
      (farm) =>
        farm.id === selectedFarmId
    );

    if (!selectedFarm) {
      return;
    }

    const usedArea =
      selectedFarm.fields.reduce(
        (total, field) =>
          total + Number(field.area),
        0
      );

    const availableArea =
      selectedFarm.area - usedArea;

    const newCropArea =
      Number(cropArea);

    if (!selectedCrop) {

      alert("Please select a crop.");

      return;
    }

    if (!cropSoil) {

      alert("Please select the soil type.");

      return;
    }

    if (!cropSeason) {

      alert("Please select the growing season.");

      return;
    }

    if (
      !newCropArea ||
      newCropArea <= 0
    ) {

      alert(
        "Please enter a valid crop area."
      );

      return;
    }

    if (
      newCropArea > availableArea
    ) {

      alert(
        `Only ${availableArea} acres are available on this farm.`
      );

      return;
    }

    const selectedCropData =
      cropOptions.find(
        (item) =>
          item.name === selectedCrop
      );

    if (!selectedCropData) {

      alert("Please select a valid crop.");

      return;
    }


    /* ----------------------------------------------
       CREATE FIELD RECORD
       Field name is generated internally.
    ---------------------------------------------- */

    const newField = {

      id: Date.now(),

      name:
        `Field ${selectedFarm.fields.length + 1}`,

      area: newCropArea,

      soilType: cropSoil,

      crop:
        selectedCropData.name,

      cropSeason: cropSeason,

      growthDuration:
        selectedCropData.growthDuration
    };


    /* ----------------------------------------------
       UPDATE FARM
    ---------------------------------------------- */

    setFarms((currentFarms) =>
      currentFarms.map((farm) => {

        if (
          farm.id !== selectedFarmId
        ) {
          return farm;
        }

        return {
          ...farm,

          fields: [
            ...farm.fields,
            newField
          ]
        };
      })
    );


    closeAddCrop();
  };
  useEffect(() => {
    const handleBrowserNavigation = (event) => {
      const nextPage =
        event.state?.page || "login";
  
      setPage(nextPage);
    };
  
    window.addEventListener(
      "popstate",
      handleBrowserNavigation
    );
  
    return () => {
      window.removeEventListener(
        "popstate",
        handleBrowserNavigation
      );
    };
  }, []);

  /* --------------------------------------------------
     LOGIN PAGE
  -------------------------------------------------- */

  if (page === "login") {

    return (

      <div className="auth-page">

        <div className="auth-overlay"></div>

        <div className="auth-content">

          <div className="brand">

            <div className="brand-icon">
              🌱
            </div>

            <div>

              <h1>
                AgriFlow
              </h1>

              <p>
                Smart agriculture. Better decisions.
              </p>

            </div>

          </div>


          <div className="auth-card">

            <div className="welcome-icon">
              🌾
            </div>

            <h2>
              Welcome back
            </h2>

            <p className="auth-subtitle">
              Continue your journey towards
              smarter farming.
            </p>


            <form
              onSubmit={handleLogin}
            >

              <label>
                Phone Number
              </label>

              <input
                type="tel"
                placeholder="Enter your phone number"
                value={loginPhone}
                onChange={(event) =>
                  setLoginPhone(
                    event.target.value
                  )
                }
                required
              />


              <label>
                Password
              </label>

              <div className="password-box">

                <input
                  type={
                    showPassword
                      ? "text"
                      : "password"
                  }
                  placeholder="Enter your password"
                  value={loginPassword}
                  onChange={(event) =>
                    setLoginPassword(
                      event.target.value
                    )
                  }
                  required
                />

                <button
                  type="button"
                  className="password-toggle"
                  onClick={() =>
                    setShowPassword(
                      !showPassword
                    )
                  }
                >
                  {showPassword
                    ? "🙈"
                    : "👁️"}
                </button>

              </div>


              <button
                className="primary-button"
                type="submit"
              >
                Sign In →
              </button>

            </form>


            <div className="divider">
              <span>
                or
              </span>
            </div>


            <p className="switch-text">

              New to AgriFlow?{" "}

              <button
  onClick={() =>
    navigateTo("signup")
  }
>
  Create an account
</button>

            </p>

          </div>


          <div className="auth-footer">
            🌱 Growing smarter, one decision at a time.
          </div>

        </div>

      </div>
    );
  }


  /* --------------------------------------------------
     SIGNUP PAGE
  -------------------------------------------------- */

  if (page === "signup") {

    return (

      <div className="auth-page signup-page">

        <div className="auth-overlay"></div>

        <div className="auth-content">

          <div className="brand">

            <div className="brand-icon">
              🌱
            </div>

            <div>

              <h1>
                AgriFlow
              </h1>

              <p>
                Your farm. Your data. Your decisions.
              </p>

            </div>

          </div>


          <div className="auth-card signup-card">

            <div className="welcome-icon">
              👨‍🌾
            </div>

            <h2>
              Create your account
            </h2>

            <p className="auth-subtitle">
              Start managing your farm with
              smarter agricultural insights.
            </p>


            <form
              onSubmit={handleSignup}
            >

              <label>
                Farmer Name
              </label>

              <input
                type="text"
                placeholder="Enter your name"
                value={signupName}
                onChange={(event) =>
                  setSignupName(
                    event.target.value
                  )
                }
                required
              />


              <label>
                Phone Number
              </label>

              <input
                type="tel"
                placeholder="10-digit phone number"
                value={signupPhone}
                onChange={(event) =>
                  setSignupPhone(
                    event.target.value
                  )
                }
                required
              />


              <label>
                Location
              </label>

              <input
                type="text"
                placeholder="Village / City"
                value={signupLocation}
                onChange={(event) =>
                  setSignupLocation(
                    event.target.value
                  )
                }
                required
              />


              <label>
                Password
              </label>

              <input
                type="password"
                placeholder="Create a password"
                value={signupPassword}
                onChange={(event) =>
                  setSignupPassword(
                    event.target.value
                  )
                }
                required
              />


              <button
                className="primary-button"
                type="submit"
              >
                Create Account →
              </button>

            </form>


            <p className="switch-text">

              Already have an account?{" "}

              <button
  onClick={() =>
    navigateTo("login")
  }
>
  Sign in
</button>

            </p>

          </div>


          <div className="auth-footer">
            🌾 Better farming begins with better information.
          </div>

        </div>

      </div>
    );
  }


  /* --------------------------------------------------
     LOGOUT PAGE
  -------------------------------------------------- */

  if (page === "logout") {

    return (

      <div className="logout-page">

        <div className="logout-sun"></div>

        <div className="logout-content">

          <div className="logout-seed">
            🌱
          </div>

          <h1>
            Every harvest begins with a decision.
          </h1>

          <p>
            Agriculture feeds communities, supports
            livelihoods and shapes our future. With
            better information and technology, every
            farming decision can become smarter.
          </p>


          <div className="growth-animation">

            <span className="seed">
              🌱
            </span>

            <span className="arrow">
              →
            </span>

            <span className="plant">
              🌿
            </span>

            <span className="arrow">
              →
            </span>

            <span className="crop">
              🌾
            </span>

          </div>


          <div className="logout-message">

            <strong>
              You have been signed out.
            </strong>

            <span>
              Thank you for growing with AgriFlow.
            </span>

          </div>


          <button
            className="primary-button logout-button"
            onClick={() =>
              navigateTo("login")
            }
          >
            Return to AgriFlow
          </button>

        </div>


        <div className="farm-silhouette">

          <span>🌾</span>
          <span>🌾</span>
          <span>🌾</span>
          <span>🌾</span>
          <span>🌾</span>
          <span>🌾</span>
          <span>🌾</span>

        </div>

      </div>
    );
  }


  /* --------------------------------------------------
     SIDEBAR
  -------------------------------------------------- */

  const Sidebar = () => (

    <aside className="sidebar">

      <div className="sidebar-brand">

        <span>
          🌱
        </span>

        <div>

          <strong>
            AgriFlow
          </strong>

          <small>
            Smart Farming
          </small>

        </div>

      </div>


      <nav>

        <button
          className={`nav-item ${
            page === "dashboard"
              ? "active"
              : ""
          }`}
          onClick={() =>
            navigateTo("dashboard")
          }
        >
          <span>📊</span>
          Dashboard
        </button>


        {/* <button className="nav-item">
          <span>👨‍🌾</span>
          My Profile
        </button> */}


        <button
          className={`nav-item ${
            page === "farms"
              ? "active"
              : ""
          }`}
          onClick={() =>
            navigateTo("farms")
          }
        >
          <span>🌾</span>
          My Farms
        </button>


        <button className="nav-item">
          <span>🌱</span>
          Fields & Crops
        </button>


        <button className="nav-item">
          <span>☁️</span>
          Weather
        </button>


        <button className="nav-item">
          <span>🤖</span>
          Crop Intelligence
        </button>


        <button className="nav-item">
          <span>📰</span>
          Agriculture Insights
        </button>

      </nav>


      <div className="sidebar-actions">

        <button className="nav-item">

          <span>
            ⚙️
          </span>

          Settings

        </button>


        <button
          className="nav-item logout-nav"
          onClick={handleLogout}
        >

          <span>
            🚪
          </span>

          Sign Out

        </button>

      </div>

    </aside>
  );


  /* --------------------------------------------------
     MY FARMS PAGE
  -------------------------------------------------- */

  if (page === "farms") {

    const totalFields =
      farms.reduce(
        (total, farm) =>
          total + farm.fields.length,
        0
      );


    const totalArea =
      farms.reduce(
        (total, farm) =>
          total + farm.area,
        0
      );


    return (

      <div className="dashboard">

        <Sidebar />


        <main className="dashboard-main">


          <header className="dashboard-header">

            <div>

              <p className="dashboard-label">
                FARM MANAGEMENT
              </p>

              <h1>
                My Farms 🌾
              </h1>

              <p className="dashboard-subtitle">
                Manage your farms and keep track
                of your agricultural land.
              </p>

            </div>


            <button
              className="primary-button"
              onClick={() =>
                setShowAddFarm(true)
              }
            >
              + Add Farm
            </button>

          </header>


          {/* FARM SUMMARY */}

          <section className="farm-summary-grid">


            <div className="farm-summary-card">

              <span>
                🌾
              </span>

              <div>

                <small>
                  Total Farms
                </small>

                <strong>
                  {farms.length}
                </strong>

              </div>

            </div>


            <div className="farm-summary-card">

              <span>
                📐
              </span>

              <div>

                <small>
                  Total Area
                </small>

                <strong>
                  {totalArea} acres
                </strong>

              </div>

            </div>


            <div className="farm-summary-card">

              <span>
                🌱
              </span>

              <div>

                <small>
                  Total Crops
                </small>

                <strong>
                  {totalFields}
                </strong>

              </div>

            </div>

          </section>


          {/* FARM SECTION */}

          <div className="farm-section-heading">

            <div>

              <h2>
                Your Farms
              </h2>

              <p>
                Select a farm to manage its crops
                and agricultural information.
              </p>

            </div>

          </div>


          <section className="farm-grid">

            {farms.map(
              (farm) => (

                <div
                  className="farm-card"
                  key={farm.id}
                >

                  <div className="farm-card-top">

                    <span className="farm-icon">
                      {farm.icon}
                    </span>

                    <span className="farm-status">
                      ACTIVE
                    </span>

                  </div>


                  <h2>
                    {farm.name}
                  </h2>


                  <p className="farm-location">
                    📍 {farm.location}
                  </p>


                  <div className="farm-details">

                    <div>

                      <span>
                        Farm Area
                      </span>

                      <strong>
                        {farm.area} acres
                      </strong>

                    </div>


                    <div>

                      <span>
                        Crops
                      </span>

                      <strong>
                        {farm.fields.length}
                      </strong>

                    </div>

                  </div>


                  <button
                    className="farm-view-button"
                    onClick={() =>
                      navigateTo(
                        `farm-${farm.id}`
                      )
                    }
                  >
                    View Farm →
                  </button>

                </div>

              )
            )}

          </section>


          {/* ADD FARM MODAL */}

          {showAddFarm && (

            <div className="farm-modal-overlay">

              <div className="farm-modal">


                <div className="farm-modal-header">

                  <div>

                    <span>
                      🌾
                    </span>

                    <div>

                      <h2>
                        Add New Farm
                      </h2>

                      <p>
                        Enter the basic details
                        of your farm.
                      </p>

                    </div>

                  </div>


                  <button
                    type="button"
                    className="modal-close"
                    onClick={() =>
                      setShowAddFarm(false)
                    }
                  >
                    ×
                  </button>

                </div>


                <form
                  onSubmit={handleAddFarm}
                >

                  <label>
                    Farm Name
                  </label>

                  <input
                    type="text"
                    placeholder="Example: Green Farm"
                    value={farmName}
                    onChange={(event) =>
                      setFarmName(
                        event.target.value
                      )
                    }
                    required
                  />


                  <label>
                    Farm Area
                  </label>

                  <div className="farm-input-with-unit">

                    <input
                      type="number"
                      min="0.1"
                      step="0.1"
                      placeholder="Enter farm area"
                      value={farmArea}
                      onChange={(event) =>
                        setFarmArea(
                          event.target.value
                        )
                      }
                      required
                    />

                    <span>
                      acres
                    </span>

                  </div>


                  <label>
                    Location
                  </label>

                  <input
                    type="text"
                    placeholder="Village / City"
                    value={farmLocation}
                    onChange={(event) =>
                      setFarmLocation(
                        event.target.value
                      )
                    }
                    required
                  />


                  <div className="farm-modal-actions">

                    <button
                      type="button"
                      className="cancel-button"
                      onClick={() =>
                        setShowAddFarm(false)
                      }
                    >
                      Cancel
                    </button>


                    <button
                      type="submit"
                      className="primary-button"
                    >
                      Add Farm →
                    </button>

                  </div>

                </form>

              </div>

            </div>

          )}

        </main>

      </div>
    );
  }


  /* --------------------------------------------------
     FARM DETAILS PAGE
  -------------------------------------------------- */

  if (page.startsWith("farm-")) {

    const farmId =
      Number(
        page.replace("farm-", "")
      );


    const selectedFarm =
      farms.find(
        (farm) =>
          farm.id === farmId
      );


    if (!selectedFarm) {

      return (

        <div className="dashboard">

          <Sidebar />

          <main className="dashboard-main">

            <div className="dashboard-card">

              <h2>
                Farm not found
              </h2>

              <button
                className="primary-button"
                onClick={() =>
                  navigateTo("farms")
                }
              >
                ← Back to My Farms
              </button>

            </div>

          </main>

        </div>
      );
    }


    /* ----------------------------------------------
       CALCULATE USED + AVAILABLE AREA
    ---------------------------------------------- */

    const usedArea =
      selectedFarm.fields.reduce(
        (total, field) =>
          total + Number(field.area),
        0
      );


    const availableArea =
      selectedFarm.area - usedArea;


    /* ----------------------------------------------
       OPENED CROP MODAL BELONGS TO THIS FARM
    ---------------------------------------------- */

    const isCropModalForThisFarm =
      showAddCrop &&
      selectedFarmId === selectedFarm.id;


    return (

      <div className="dashboard">

        <Sidebar />


        <main className="dashboard-main">


          {/* BACK BUTTON */}

          <button
            className="farm-back-button"
            onClick={() =>
              setPage("farms")
            }
          >
            ← Back to My Farms
          </button>


          {/* FARM HEADER */}

          <header className="dashboard-header farm-details-header">

            <div>

              <p className="dashboard-label">
                FARM DETAILS
              </p>

              <h1>
                {selectedFarm.name}{" "}
                {selectedFarm.icon}
              </h1>

              <p className="dashboard-subtitle">
                📍 {selectedFarm.location}
              </p>

            </div>


            <span className="farm-status">
              ACTIVE
            </span>

          </header>


          {/* FARM SUMMARY */}

          <section className="farm-detail-summary">


            <div className="farm-detail-card">

              <span className="farm-detail-icon">
                📐
              </span>

              <div>

                <small>
                  Farm Area
                </small>

                <strong>
                  {selectedFarm.area} acres
                </strong>

              </div>

            </div>


            <div className="farm-detail-card">

              <span className="farm-detail-icon">
                🌱
              </span>

              <div>

                <small>
                  Crops
                </small>

                <strong>
                  {selectedFarm.fields.length}
                </strong>

              </div>

            </div>


            <div className="farm-detail-card">

              <span className="farm-detail-icon">
                📍
              </span>

              <div>

                <small>
                  Location
                </small>

                <strong>
                  {selectedFarm.location}
                </strong>

              </div>

            </div>

          </section>


          {/* CROPS SECTION */}

          <section className="dashboard-card farm-fields-card">


            <div className="card-heading">

              <div>

                <span className="heading-icon">
                  🌱
                </span>

                <div>

                  <h2>
                    Crops on this Farm
                  </h2>

                  <p>
                    Add the crops you are growing
                    and the area used for each crop.
                  </p>

                </div>

              </div>


              <button
                type="button"
                className="secondary-button farm-action-button"
                onClick={() =>
                  openAddCrop(
                    selectedFarm.id
                  )
                }
              >
                + Add Crop
              </button>

            </div>


            {/* AREA SUMMARY */}

            <div className="farm-area-summary">


              <div>

                <span>
                  Farm Area
                </span>

                <strong>
                  {selectedFarm.area} acres
                </strong>

              </div>


              <div>

                <span>
                  Used Area
                </span>

                <strong>
                  {usedArea} acres
                </strong>

              </div>


              <div>

                <span>
                  Available
                </span>

                <strong>
                  {availableArea} acres
                </strong>

              </div>

            </div>


            {/* CROP LIST */}

            {selectedFarm.fields.length > 0 ? (

              <div className="fields-list">

                {selectedFarm.fields.map(
                  (field) => {

                    const cropInfo =
                      cropOptions.find(
                        (item) =>
                          item.name ===
                          field.crop
                      );


                    return (

                      <div
                        className="field-card"
                        key={field.id}
                      >


                        <div className="field-card-header">

                          <div>

                            <h3>

                              {cropInfo?.icon ||
                                "🌱"}{" "}

                              {field.crop}

                            </h3>

                            <span>
                              {field.area} acres
                            </span>

                          </div>


                          <span className="field-crop">
                            {field.cropSeason}
                          </span>

                        </div>


                        <div className="field-details">


                          <div>

                            <small>
                              Soil Type
                            </small>

                            <strong>
                              {field.soilType}
                            </strong>

                          </div>


                          <div>

                            <small>
                              Growth Duration
                            </small>

                            <strong>
                              {field.growthDuration} days
                            </strong>

                          </div>


                          <div>

                            <small>
                              Land Allocation
                            </small>

                            <strong>
                              {field.area} acres
                            </strong>

                          </div>

                        </div>

                      </div>
                    );
                  }
                )}

              </div>

            ) : (

              /* ------------------------------------
                 NO CROPS
              ------------------------------------ */

              <div className="farm-empty-state">

                <span>
                  🌱
                </span>

                <h3>
                  No crops added yet
                </h3>

                <p>
                  Add your first crop to start
                  tracking this farm in AgriFlow.
                </p>


                <button
                  type="button"
                  className="secondary-button farm-action-button"
                  onClick={() =>
                    openAddCrop(
                      selectedFarm.id
                    )
                  }
                >
                  + Add First Crop
                </button>

              </div>

            )}


          </section>


          {/* ------------------------------------------
              ADD CROP MODAL
          ------------------------------------------ */}

          {isCropModalForThisFarm && (

            <div
              className="modal-overlay"
              onClick={closeAddCrop}
            >

              <div
                className="modal-card"
                onClick={(event) =>
                  event.stopPropagation()
                }
              >


                {/* MODAL HEADER */}

                <div className="modal-header">

                  <div>

                    <p className="dashboard-label">
                      ADD CROP
                    </p>

                    <h2>
                      What are you growing?
                    </h2>

                    <p>
                      Add a crop to{" "}
                      {selectedFarm.name}.
                    </p>

                  </div>


                  <button
                    type="button"
                    className="modal-close"
                    onClick={closeAddCrop}
                  >
                    ×
                  </button>

                </div>


                {/* FORM */}

                <form
                  onSubmit={handleAddCrop}
                >


                  {/* CROP */}

                  <div className="form-group">

                    <label>
                      Select Crop
                    </label>

                    <select
                      value={selectedCrop}
                      onChange={(event) =>
                        setSelectedCrop(
                          event.target.value
                        )
                      }
                      required
                    >

                      <option value="">
                        Choose a crop
                      </option>


                      {cropOptions.map(
                        (item) => (

                          <option
                            key={item.name}
                            value={item.name}
                          >
                            {item.icon}{" "}
                            {item.name}
                          </option>

                        )
                      )}

                    </select>

                  </div>


                  {/* LAND AREA */}

                  <div className="form-group">

                    <label>
                      Land Used
                    </label>


                    <div className="farm-input-with-unit">

                      <input
                        type="number"
                        min="0.1"
                        max={
                          availableArea > 0
                            ? availableArea
                            : undefined
                        }
                        step="0.1"
                        placeholder="How much land?"
                        value={cropArea}
                        onChange={(event) =>
                          setCropArea(
                            event.target.value
                          )
                        }
                        required
                      />

                      <span>
                        acres
                      </span>

                    </div>


                    <small className="form-helper">

                      Available land:{" "}

                      {availableArea} acres

                    </small>

                  </div>


                  {/* SOIL */}

                  <div className="form-group">

                    <label>
                      Soil Type
                    </label>


                    <select
                      value={cropSoil}
                      onChange={(event) =>
                        setCropSoil(
                          event.target.value
                        )
                      }
                      required
                    >

                      <option value="">
                        Select soil type
                      </option>


                      {soilOptions.map(
                        (soil) => (

                          <option
                            key={soil}
                            value={soil}
                          >
                            {soil}
                          </option>

                        )
                      )}

                    </select>

                  </div>


                  {/* SEASON */}

                  <div className="form-group">

                    <label>
                      Growing Season
                    </label>


                    <select
                      value={cropSeason}
                      onChange={(event) =>
                        setCropSeason(
                          event.target.value
                        )
                      }
                      required
                    >

                      <option value="">
                        Select season
                      </option>


                      {seasonOptions.map(
                        (season) => (

                          <option
                            key={season}
                            value={season}
                          >
                            {season}
                          </option>

                        )
                      )}

                    </select>

                  </div>


                  {/* GROWTH DURATION */}

                  {selectedCrop && (

                    <div className="crop-duration-preview">

                      <span>
                        🌱
                      </span>

                      <div>

                        <small>
                          Expected growth duration
                        </small>

                        <strong>

                          {
                            cropOptions.find(
                              (item) =>
                                item.name ===
                                selectedCrop
                            )?.growthDuration
                          }{" "}

                          days

                        </strong>

                      </div>

                    </div>

                  )}


                  {/* ACTION BUTTONS */}

                  <div className="form-actions">

                    <button
                      type="button"
                      className="secondary-button"
                      onClick={closeAddCrop}
                    >
                      Cancel
                    </button>


                    <button
                      type="submit"
                      className="primary-button"
                    >
                      Add Crop
                    </button>

                  </div>

                </form>

              </div>

            </div>

          )}


          {/* ------------------------------------------
              CROP INTELLIGENCE + WEATHER
          ------------------------------------------ */}

          <section className="farm-info-section">


            <div className="dashboard-card">

              <div className="card-heading">

                <div>

                  <span className="heading-icon">
                    🤖
                  </span>

                  <div>

                    <h2>
                      Crop Intelligence
                    </h2>

                    <p>
                      AI-powered insights for this farm.
                    </p>

                  </div>

                </div>

              </div>


              <div className="farm-info-placeholder">

                <span>
                  🌾
                </span>

                <p>
                  Add crop information to receive
                  agricultural recommendations based
                  on your farm conditions and weather.
                </p>

              </div>

            </div>


            <div className="dashboard-card">

              <div className="card-heading">

                <div>

                  <span className="heading-icon">
                    ☁️
                  </span>

                  <div>

                    <h2>
                      Farm Weather
                    </h2>

                    <p>
                      Weather conditions for this farm.
                    </p>

                  </div>

                </div>

              </div>


              {weatherLoading ? (
  <div className="farm-weather-placeholder">
    <strong>...</strong>
    <span>☁️ Loading weather...</span>
  </div>
) : weatherError ? (
  <div className="farm-weather-placeholder">
    <strong>⚠️</strong>
    <span>{weatherError}</span>
  </div>
) : farmWeather ? (
  <div className="farm-weather-live">
    <div className="weather-main-value">
      <strong>
        {farmWeather.weather.current.temperature_2m}°C
      </strong>
      <span>🌤️ Current temperature</span>
    </div>

    <div className="weather-mini-grid">
      <div>
        <small>Humidity</small>
        <strong>
          {farmWeather.weather.current.relative_humidity_2m}%
        </strong>
      </div>

      <div>
        <small>Rainfall</small>
        <strong>
          {farmWeather.weather.current.precipitation} mm
        </strong>
      </div>

      <div>
        <small>Wind</small>
        <strong>
          {farmWeather.weather.current.wind_speed_10m} km/h
        </strong>
      </div>

      <div>
        <small>Rain probability</small>
        <strong>
          {farmWeather.maximumRainProbability}%
        </strong>
      </div>
    </div>
  </div>
) : null}

            </div>

          </section>


        </main>

      </div>
    );
  }


  /* --------------------------------------------------
     DASHBOARD
  -------------------------------------------------- */

  const totalFields =
    farms.reduce(
      (total, farm) =>
        total + farm.fields.length,
      0
    );


  return (

    <div className="dashboard">

      <Sidebar />


      <main className="dashboard-main">


        {/* DASHBOARD HEADER */}

        <header className="dashboard-header">

          <div>

            <p className="dashboard-label">
              FARMER DASHBOARD
            </p>

            <h1>
              Good morning,{" "}
              {farmerName || "Farmer"} 👋
            </h1>

            <p className="dashboard-subtitle">
              Manage your farm and make smarter
              agricultural decisions.
            </p>

          </div>


          <div className="profile-circle">

            {farmerName
              ? farmerName
                  .charAt(0)
                  .toUpperCase()
              : "F"}

          </div>

        </header>


        {/* STAT CARDS */}

        <section className="stat-grid">


          <div className="stat-card">

            <div className="stat-icon green">
              🌾
            </div>

            <div>

              <span>
                My Farms
              </span>

              <strong>
                {farms.length}
              </strong>

            </div>

          </div>


          <div className="stat-card">

            <div className="stat-icon brown">
              🌱
            </div>

            <div>

              <span>
                My Crops
              </span>

              <strong>
                {totalFields}
              </strong>

            </div>

          </div>


          <div className="stat-card">

            <div className="stat-icon blue">
              ☁️
            </div>

            <div>

              <span>
                Temperature
              </span>

              <strong>
                32°C
              </strong>

            </div>

          </div>


          <div className="stat-card">

            <div className="stat-icon yellow">
              🌧️
            </div>

            <div>

              <span>
                Rain Probability
              </span>

              <strong>
                40%
              </strong>

            </div>

          </div>

        </section>


        {/* DASHBOARD MAIN CARDS */}

        <section className="dashboard-grid">


          {/* CROP INTELLIGENCE */}

          <div className="dashboard-card intelligence-card">

            <div className="card-heading">

              <div>

                <span className="heading-icon">
                  🤖
                </span>

                <div>

                  <h2>
                    Crop Intelligence
                  </h2>

                  <p>
                    AI-powered crop recommendation
                  </p>

                </div>

              </div>


              <span className="ai-badge">
                AI POWERED
              </span>

            </div>


            <div className="recommendation-preview">

              <div className="crop-visual">
                🌾
              </div>

              <div>

                <span className="recommended-label">
                  CROP RECOMMENDATION
                </span>

                <h3>
                  Discover the right crop
                </h3>

                <p>
                  Use your field conditions and
                  live weather data to discover
                  suitable crops.
                </p>

                <button className="secondary-button">
                  Explore Crop Intelligence →
                </button>

              </div>

            </div>

          </div>


          {/* WEATHER */}

          <div className="dashboard-card weather-card">

            <div className="card-heading">

              <div>

                <span className="heading-icon">
                  ☁️
                </span>

                <div>

                  <h2>
                    Weather
                  </h2>

                  <p>
                    Current conditions
                  </p>

                </div>

              </div>

            </div>


            <div className="weather-main">

              <span>
                ☀️
              </span>

              <div>

                <strong>
                  32°C
                </strong>

                <p>
                  Current temperature
                </p>

              </div>

            </div>


            <div className="weather-details">

              <div>

                <span>
                  💧 Humidity
                </span>

                <strong>
                  56%
                </strong>

              </div>


              <div>

                <span>
                  🌧️ Rainfall
                </span>

                <strong>
                  0 mm
                </strong>

              </div>

            </div>

          </div>

        </section>


        {/* CHARTS */}

        <section className="charts-section">


          {/* WEATHER CHART */}

          <div className="dashboard-card chart-card">

            <div className="card-heading">

              <div>

                <span className="heading-icon">
                  🌤️
                </span>

                <div>

                  <h2>
                    Weather Trend
                  </h2>

                  <p>
                    Temperature over the last 7 days
                  </p>

                </div>

              </div>


              <span className="chart-label">
                7 DAYS
              </span>

            </div>


            <div className="chart-container">

              <ResponsiveContainer
                width="100%"
                height={280}
              >

                <LineChart
                  data={weatherData}
                >

                  <CartesianGrid
                    strokeDasharray="3 3"
                  />

                  <XAxis
                    dataKey="day"
                  />

                  <YAxis />

                  <Tooltip />

                  <Line
                    type="monotone"
                    dataKey="temperature"
                    strokeWidth={3}
                    dot={{ r: 5 }}
                    name="Temperature"
                  />

                </LineChart>

              </ResponsiveContainer>

            </div>

          </div>


          {/* CROP CHART */}

          <div className="dashboard-card chart-card">

            <div className="card-heading">

              <div>

                <span className="heading-icon">
                  🌾
                </span>

                <div>

                  <h2>
                    Crop Distribution
                  </h2>

                  <p>
                    Fields by crop type
                  </p>

                </div>

              </div>

            </div>


            <div className="chart-container">

              <ResponsiveContainer
                width="100%"
                height={280}
              >

                <BarChart
                  data={cropData}
                >

                  <CartesianGrid
                    strokeDasharray="3 3"
                  />

                  <XAxis
                    dataKey="crop"
                  />

                  <YAxis
                    allowDecimals={false}
                  />

                  <Tooltip />

                  <Bar
                    dataKey="fields"
                    name="Fields"
                    radius={[
                      6,
                      6,
                      0,
                      0
                    ]}
                  />

                </BarChart>

              </ResponsiveContainer>

            </div>

          </div>

        </section>


        {/* IMPORTANCE SECTION */}

        <section className="importance-section">


          <div className="section-title">

            <span>
              🌍
            </span>

            <div>

              <h2>
                Why smarter farming matters
              </h2>

              <p>
                Technology can help turn
                agricultural data into useful
                decisions.
              </p>

            </div>

          </div>


          <div className="importance-grid">


            <div>

              <span>
                🌾
              </span>

              <h3>
                Food Security
              </h3>

              <p>
                Farming provides the food
                communities depend on every day.
              </p>

            </div>


            <div>

              <span>
                💧
              </span>

              <h3>
                Better Decisions
              </h3>

              <p>
                Data can help farmers understand
                changing field and weather conditions.
              </p>

            </div>


            <div>

              <span>
                🌍
              </span>

              <h3>
                Sustainable Future
              </h3>

              <p>
                Smarter agricultural practices
                can support responsible resource use.
              </p>

            </div>


            <div>

              <span>
                🤖
              </span>

              <h3>
                Technology
              </h3>

              <p>
                Weather intelligence and machine
                learning can support modern
                farming decisions.
              </p>

            </div>

          </div>

        </section>


      </main>

    </div>
  );
}

export default App;

