package com.service.presentation.utils.bundle

import java.lang.Exception

class MissingBundleArgumentException(key: String): Exception("Missing ($key) in Bundle Arguments.")