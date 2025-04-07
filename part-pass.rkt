;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname part-pass) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define part-pass-are-ire ;val: stringa
  (lambda (s) ;s: stringa
    (string-append (substring s 0 (- (string-length s) 2)) "to")
    ))

(define part-pass-ere ;val: stringa
  (lambda (s) ;s: stringa
    (string-append (substring s 0 (- (string-length s) 3)) "uto")
   ))

(define part-pass ;val: stringa
  (lambda (s) ;s:stringa
    (if (char=? (string-ref s (- (string-length s) 3)) #\e) (part-pass-ere s) (part-pass-are-ire s))
    ))
