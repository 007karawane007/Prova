;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname complementare-a-uno) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define bit-compl ;val numero binario
  (lambda (bit)
    (if (string=? bit "0") "1" "0"
    )))

(define compl-uno-v2 ;val numero binario
  (lambda (seq)
    (if (> (string-length seq) 1)
        (string-append (compl-uno-v2 (substring seq 0 (- (string-length seq) 1)));(bit-compl (substring seq (- (string-length seq) 1)))
                       (bit-compl (substring seq (- (string-length seq) 1)));(compl-uno-v2 (substring seq 0 (- (string-length seq) 1)))
                       )
        (bit-compl seq)
        )))

(define compl-uno-v1
  (lambda (seq)
    (if (> (string-length seq) 1)
        (string-append (bit-compl (substring seq 0 1))
                       (compl-uno-v1 (substring seq 1)))
        (bit-compl seq)
        )))

(define compl1
  (lambda (seq)
    (if (>= (string-length seq) 2)
        (let ((k (quotient (string-length seq) 2)))
          (string-append (compl1 (substring seq 0 k)) (compl1 (substring seq k)))
          )
        (bit-compl seq)
        )
    ))

(define bin-val
  (lambda (bin)
    (let ((n (string-length bin))
          )
      (if (= n 1)
          (bit-val bin)
          (+ (* 2 (bin-val (substring bin 0 (- n 1))))
          (bit-val (substring bin (- n 1))))
      )
      )
    ))

(define bit-val
  (lambda (bit)
    (if (string=? bit "0")
        0
        1
        )
    ))

