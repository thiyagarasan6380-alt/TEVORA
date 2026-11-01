import { Link } from "react-router-dom";

function Sidebar({ mobileOpen, setMobileOpen }) {

    return (
        <>
            {/* Mobile overlay */}
            {mobileOpen && (
                <div
                    className="fixed inset-0 bg-black/40 z-40 md:hidden"
                    onClick={() => setMobileOpen(false)}
                ></div>
            )}

            <div
                className={`
                    w-64 h-screen flex-shrink-0
                    bg-gradient-to-b from-orange-500 to-orange-700
                    text-white p-5
                    fixed md:static
                    top-0 left-0 z-50
                    transform transition-transform duration-300
                    ${mobileOpen ? "translate-x-0" : "-translate-x-full"}
                    md:translate-x-0
                `}
            >

                <div className="mb-10 text-center">

                    <h1 className="text-2xl font-bold">
                        TEVORA
                    </h1>

                    <p className="text-sm">
                        Find. Collaborate. Build.
                    </p>

                </div>

                <div className="flex flex-col gap-4">

                    <Link
                        className="rounded-lg p-3 hover:bg-orange-500"
                        to="/"
                        onClick={() => setMobileOpen(false)}
                    >
                        Home
                    </Link>

                    <Link
                        className="rounded-lg p-3 hover:bg-orange-500"
                        to="/projects"
                        onClick={() => setMobileOpen(false)}
                    >
                        Projects
                    </Link>

                    <Link
                        className="rounded-lg p-3 hover:bg-orange-500"
                        to="/MyApplication"
                        onClick={() => setMobileOpen(false)}
                    >
                        MyApplication
                    </Link>

                    <Link
                        className="rounded-lg p-3 hover:bg-orange-500"
                        to="/createproject"
                        onClick={() => setMobileOpen(false)}
                    >
                        Create Project
                    </Link>

                    <Link
                        className="rounded-lg p-3 hover:bg-orange-500"
                        to="/login"
                        onClick={() => setMobileOpen(false)}
                    >
                        Login
                    </Link>

                </div>

            </div>
        </>
    );
}

export default Sidebar;
