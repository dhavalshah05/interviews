package com.template.app.util.bundle

import java.lang.Exception

class MissingBundleArgumentException(key: String): Exception("Missing ($key) in Bundle Arguments.")